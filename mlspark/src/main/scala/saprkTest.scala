import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.feature.{HashingTF, Tokenizer}
import org.apache.spark.ml.tuning.{ParamGridBuilder, CrossValidator}
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.sql.{SQLContext, Row}

/**
  * 功 能: 
  * 创建人: LDL
  * 时 间:  2016/2/28 16:16
  */
object saprkTest {

    def main(args: Array[String]) {
        val conf = new SparkConf( ).setAppName( "tt" ).setMaster( "local" )
        val sc = new SparkContext(conf)
        val sqlContext = new SQLContext(sc)
        // Prepare training data from a list of (id, text, label) tuples.
        val training = sqlContext.createDataFrame(Seq(
            (0L, "苹果 官网 苹果 宣布",0.0),
            (1L, "苹果 梨 香蕉", 1.0)
        )).toDF("id", "text","label")

        // Configure an ML pipeline, which consists of three stages: tokenizer, hashingTF, and lr.
        val tokenizer = new Tokenizer()
                .setInputCol("text")
                .setOutputCol("words")
        val hashingTF = new HashingTF()
                .setInputCol(tokenizer.getOutputCol)
                .setOutputCol("features")
        val lr = new LogisticRegression()
                .setMaxIter(10)
        val pipeline = new Pipeline()
                .setStages(Array(tokenizer, hashingTF, lr))

        // We use a ParamGridBuilder to construct a grid of parameters to search over.
        // With 3 values for hashingTF.numFeatures and 2 values for lr.regParam,
        // this grid will have 3 x 2 = 6 parameter settings for CrossValidator to choose from.
        val paramGrid = new ParamGridBuilder()
                .addGrid(hashingTF.numFeatures, Array(10, 100, 1000))
                .addGrid(lr.regParam, Array(0.1, 0.01))
                .build()

        // We now treat the Pipeline as an Estimator, wrapping it in a CrossValidator instance.
        // This will allow us to jointly choose parameters for all Pipeline stages.
        // A CrossValidator requires an Estimator, a set of Estimator ParamMaps, and an Evaluator.
        // Note that the evaluator here is a BinaryClassificationEvaluator and its default metric
        // is areaUnderROC.
        val cv = new CrossValidator()
                .setEstimator(pipeline)
                .setEvaluator(new BinaryClassificationEvaluator)
                .setEstimatorParamMaps(paramGrid)
                .setNumFolds(2) // Use 3+ in practice

        // Run cross-validation, and choose the best set of parameters.
        val cvModel = cv.fit(training)

        // Prepare test documents, which are unlabeled (id, text) tuples.
        val test = sqlContext.createDataFrame(Seq(
            (4L, "苹果 水果 橘子")
        )).toDF("id", "text")

        // Make predictions on test documents. cvModel uses the best model found (lrModel).
        cvModel.transform(test)
                .select("id", "text", "probability", "prediction")
                .collect()
                .foreach { case Row(id: Long, text: String, prob: Vector, prediction: Double) =>
                    println(s"($id, $text) --> prob=$prob, prediction=$prediction")
                }
    }
}
