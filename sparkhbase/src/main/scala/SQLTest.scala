import org.apache.spark.SparkContext
import org.apache.spark.sql.SQLContext

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/10 11:07
 */
object SQLTest {
    def main(args: Array[String]) {
        System.setProperty("hadoop.home.dir", "F:\\data\\hadoop-common-2.2.0-bin-master")
        val sc = new SparkContext("local", "SparkHBase")
        // sc is an existing SparkContext.
        val sqlContext = new SQLContext(sc)

        val people = sqlContext.read.json("F:\\data.json")
        people.registerTempTable("people")
        val teenagers = sqlContext.sql("SELECT title FROM people where _id = '848705b3768ca65a'")
        println(teenagers.toJavaRDD.collect())

    }
}
