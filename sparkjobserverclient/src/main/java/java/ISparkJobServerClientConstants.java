package java;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:22
 */
public class ISparkJobServerClientConstants {
    /**
     * Parameter used to create job context, indicates the number of cpu cores
     * for current spark job context.
     * <p>It's value is integer.
     * <p>It is equivalent to <code>PARAM_SPARK_CORES_MAX</code>.
     */
    static final String PARAM_NUM_CPU_CORES = "num-cpu-cores";

    /**
     * Parameter used to create job context, indicates the number of cpu cores
     * for current spark job context.
     * <p>It's value is integer.
     * <p>It is equivalent to <code>PARAM_NUM_CPU_CORES</code>.
     */
    static final String PARAM_SPARK_CORES_MAX = "spark.cores.max";

    /**
     * Parameter used to create job context, indicates the number of memeory
     * for each spark job executor.
     * <p>It's value is 'xxxm' such as 512m, where xxx is a integer, and m means MB.
     * <p>It is equivalent to <code>PARAM_SPARK_EXECUTOR_MEMORY</code>.
     */
    static final String PARAM_MEM_PER_NODE = "mem-per-node";

    /**
     * Parameter used to create job context, indicates the number of memeory
     * for each spark job executor.
     * <p>It's value is 'xxxm' such as 512m, where xxx is a integer, and m means MB.
     * <p>It is equivalent to <code>PARAM_MEM_PER_NODE</code>.
     */
    static final String PARAM_SPARK_EXECUTOR_MEMORY = "spark.executor.memory";

    /**
     * It's a necessary parameter used to assign a application name when
     * trying to start a new job.
     */
    static final String PARAM_APP_NAME = "appName";

    /**
     * It's a necessary parameter which is a full qualified class name such as
     * <code>'spark.jobserver.WorkCountExample'</code>, used to assign a spark
     * job main class when trying to start a new job.
     */
    static final String PARAM_CLASS_PATH = "classPath";

    /**
     * It's an optional parameter which is an existing context in the calling of
     * <code>GET /contexts</code>, used to assign a context in which the new
     * created job will run.
     * <p>When it wants the new started job to run synchronize and gets the result,
     * It becomes a necessary one.
     */
    static final String PARAM_CONTEXT = "context";

    /**
     * It's an optional parameter which could be "true" or "false",
     * which is used to identify the client will wait for created
     * job 's finish and get the results.
     * <p>When it wants the new started job to run synchronize and gets the result,
     * It becomes a necessary one.
     */
    static final String PARAM_SYNC = "sync";
}
