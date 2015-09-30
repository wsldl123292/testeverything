package java;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:24
 */
public class SparkJobInfo extends SparkJobBaseInfo {
    /**
     * Key of duration information in the Spark Job Server's json response.
     * <p>The value shows the execution time of the target spark job.
     */
    static final String INFO_KEY_DURATION = "duration";

    /**
     * Key of classPath information in the Spark Job Server's json response.
     * <p>The value shows the spark job main class which extends class of <code>SparkJob</code>.
     */
    static final String INFO_KEY_CLASSPATH = "classPath";

    /**
     * Key of startTime information in the Spark Job Server's json response.
     * <p>The value shows start time of the target spark job.
     */
    static final String INFO_KEY_START_TIME = "startTime";

    private String duration;
    private String classPath;
    private String startTime;


    public String getDuration() {
        return duration;
    }

    void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClassPath() {
        return classPath;
    }

    void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getStartTime() {
        return startTime;
    }

    void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder buff = new StringBuilder("SparkJobInfo");
        buff.append("{\n");
        buff.append(" ").append(INFO_KEY_DURATION).append(": ")
                .append(this.getDuration() != null ? this.getDuration() : INFO_EMPTY_VALUE).append("\n")
                .append(" ").append(INFO_KEY_CLASSPATH).append(": ")
                .append(this.getClassPath() != null ? this.getClassPath() : INFO_EMPTY_VALUE).append("\n")
                .append(" ").append(INFO_KEY_START_TIME).append(": ")
                .append(this.getStartTime() != null ? this.getStartTime() : INFO_EMPTY_VALUE).append("\n")
                .append(" ").append(INFO_KEY_CONTEXT).append(": ")
                .append(this.getContext() != null ? this.getContext() : INFO_EMPTY_VALUE).append("\n")
                .append(" ").append(INFO_KEY_JOB_ID).append(": ")
                .append(this.getJobId() != null ? this.getJobId() : INFO_EMPTY_VALUE).append("\n")
                .append(" ").append(INFO_KEY_STATUS).append(": ")
                .append(this.getStatus() != null ? this.getStatus() : INFO_EMPTY_VALUE).append("\n");
        if (this.getMessage() != null) {
            buff.append(" ").append(INFO_KEY_RESULT).append(": {\n")
                    .append("  ").append(INFO_KEY_RESULT_MESSAGE).append(": ").append(getMessage()).append("\n");
        }
        if (this.getErrorClass() != null) {
            buff.append("  ").append(INFO_KEY_RESULT_ERROR_CLASS).append(": ").append(getErrorClass()).append("\n");
        }
        if (this.getStack() != null) {
            buff.append("  ").append(INFO_KEY_RESULT_STACK).append(": [");
            for (String stackItem : getStack()) {
                buff.append(" ").append(stackItem).append(",\n");
            }
            buff.append("  ]");
        }

        buff.append("}");
        return buff.toString();
    }
}
