import org.apache.spark.{SparkConf, SparkContext}

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/10 11:07
 */
object SQLTest {
    def main(args: Array[String]) {
        System.setProperty("hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master")
        val conf = new SparkConf().setAppName("a").setMaster("local")
        val sc = new SparkContext(conf)

        val input = sc.textFile("F:\\data.txt").map(line => line.split(" ").toSeq)
        print(input.collect().toList)

    }
}
