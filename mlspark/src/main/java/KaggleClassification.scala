import org.apache.spark.mllib.classification.{NaiveBayes, SVMWithSGD, LogisticRegressionWithSGD}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.configuration.Algo
import org.apache.spark.mllib.tree.impurity.Entropy
import org.apache.spark.{SparkConf, SparkContext}
/**
 * 作者: LDL
 * 说明:
 * 时间: 2015/9/20 17:19
 */
object KaggleClassification {

    def main(args: Array[ String ]) {
        val conf = new SparkConf( ).setAppName( "kaggleClassificationTest" ).setMaster( "local" )
        val sc = new SparkContext(conf)
        val rowData = sc.textFile("/user/root/train_noheader.tsv")

        val records = rowData.map(line=>line.split("\t"))

        val data = records.map{ r=>

            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(4,r.size-1).map(d=> if(d=="?") 0.0 else d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }

        data.cache()

        val nbdata = records.map{ r=>

            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(4,r.size-1).map(d=> if(d=="?") 0.0 else d.toDouble).map(d => if(d < 0 )0.0 else d)
            LabeledPoint(label,Vectors.dense(features))
        }
        nbdata.cache()

        var numIteration = 10
        val maxTreeDeth = 5
        val lrModel = LogisticRegressionWithSGD.train(data,numIteration)

        val svmModel = SVMWithSGD.train(data,numIteration)

        val nbModel = NaiveBayes.train(nbdata)

        val dtModel = DecisionTree.train(data,Algo.Classification,Entropy,maxTreeDeth)

        val dataPoint = data.first()
        val prediction = lrModel.predict(dataPoint.features)
        print("lrModel.............."+prediction)

        val trueLabel = dataPoint.label
        print("label.............."+trueLabel)

        val predictions = lrModel.predict(data.map(lp=>lp.features))
        predictions.take(5)
        sc.stop()
    }
}
