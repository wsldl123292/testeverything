import com.typesafe.config._
import org.apache.spark._
import org.apache.spark.api.java.JavaSparkContext
import spark.jobserver.{NamedRddSupport, SparkJob, SparkJobValid, SparkJobValidation}

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/17 10:59
 */
object JavaWord extends SparkJob with NamedRddSupport {
    /*def main(args: Array[String]) {
        val ctx = new SparkContext("local[4]", "JavaWordCount")
        val config = ConfigFactory.parseString("")
        val results = runJob(ctx, config)
        println("Result is " + results)
    }*/

    override def validate(sc: SparkContext, config: Config): SparkJobValidation = {
        SparkJobValid
    }

    override def runJob(sc: SparkContext, config: Config): Any = {
        val jsc = new JavaSparkContext(sc)
        val j = new JavaCount()
        val results = j.runJob(jsc: JavaSparkContext, config: Config)
        this.namedRdds.update("javaresult", results);
    }
}
