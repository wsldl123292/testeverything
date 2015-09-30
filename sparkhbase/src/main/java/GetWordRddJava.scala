import com.typesafe.config.Config
import org.apache.spark.SparkContext
import spark.jobserver._

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/17 13:49
 */
object GetWordRddJava extends SparkJob with NamedRddSupport {
    override def runJob(sc: SparkContext, jobConfig: Config): Any = {
        val rdd = this.namedRdds.get[(String,Int)]("javaresult").get
        //rdd.map((_, 1)).reduceByKey(_ + _).collect().toMap
    }

    override def validate(sc: SparkContext, config: Config): SparkJobValidation = {
        val rdd = this.namedRdds.get[(String,Int)]("javaresult")
        if (rdd.isDefined) SparkJobValid else SparkJobInvalid(s"Missing named RDD [javaresult]")
    }

}
