import com.typesafe.config.Config
import org.apache.spark.SparkContext
import org.apache.spark.api.java.JavaSparkContext
import spark.jobserver.{SparkJobValid, SparkJobInvalid, SparkJob, SparkJobValidation}

/**
  * 作者: LDL
  * 功能说明:
  * 创建日期: 2015/9/16 19:08
  */
class JavaSparkJob extends SparkJob {
    def runJob(sc: SparkContext, jobConfig: Config): Any = {
        runJob(new JavaSparkContext(sc), jobConfig);
    }

    def validate(sc: SparkContext, config: Config): SparkJobValidation = {
        Option(invalidate(new JavaSparkContext(sc), config))
                .map(err => SparkJobInvalid(err))
                .getOrElse(SparkJobValid)
    }

    /**
     * The main class that carries out the Spark job.  The results will be converted to JSON
     * and emitted (but NOT persisted).
     */
    def runJob(jsc: JavaSparkContext, jobConfig: Config): Any = {}

    /**
     * Checks the config and returns an error message, or null if the config is fine.
     * The error message will be returned to the user as a 404 HTTP error code.
     */
    def invalidate(jsc: JavaSparkContext, config: Config): String = { null }
}
