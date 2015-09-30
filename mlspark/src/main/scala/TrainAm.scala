import org.apache.spark.SparkContext
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.tree.DecisionTree
import org.apache.spark.mllib.tree.configuration.Algo
import org.apache.spark.mllib.tree.impurity.Entropy

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/30 14:37
 */
object TrainAm {
    def main(args: Array[String]) {
        System.setProperty( "hadoop.home.dir", "F:\\tools\\hadoop-common-2.2.0-bin-master" )
        val sc = new SparkContext("local[2]", "am")

        // we take the raw data in CSV format and convert it into a set of records of the form (user, product, price)
        val rowData = sc.textFile("D:\\train_am.txt")

        val records = rowData.map(line=>line.split("\t"))

        val data = records.map{ r=>

            val trimmed = r.map(_.replaceAll("\"",""))
            val label = trimmed(r.size-1).toInt
            val features = trimmed.slice(0,r.size-2).map(d=> d.toDouble)
            LabeledPoint(label,Vectors.dense(features))
        }

        data.cache()


        var numIteration = 10
        val maxTreeDeth = 5
        //val lrModel = LogisticRegressionWithSGD.train(data,numIteration)

        //val svmModel = SVMWithSGD.train(data,numIteration)

        val dtModel = DecisionTree.train(data,Algo.Classification,Entropy,maxTreeDeth,5)

        val dataPoint = data.first()
        val predictions = dtModel.predict(data.map(lp=>lp.features))
        //print("lrModel.............."+prediction)
        print("label.............."+predictions.take(5).toList)
        //val trueLabel = dataPoint.label
        //print("label.............."+trueLabel)

        /*val predictions = lrModel.predict(data.map(lp=>lp.features))
        predictions.take(5)*/
        sc.stop()
    }
}
