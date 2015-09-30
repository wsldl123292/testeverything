package java;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:25
 */
public class SparkJobJarInfo {
    /**
     * empty value
     */
    private static final String INFO_EMPTY_VALUE = "empty value";
    /**
     * jarName
     */
    static final String INFO_KEY_JAR_NAME = "jarName";
    /**
     * uploadedTime
     */
    static final String INFO_KEY_UPLOADED_TIME = "uploadedTime";

    /**
     * jar名称
     */
    private String jarName;
    /**
     * 上传时间
     */
    private String uploadedTime;

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(String uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "SparkJobJarInfo{\n" + " " + INFO_KEY_JAR_NAME + ": " + (this.getJarName() != null ? this.getJarName() : INFO_EMPTY_VALUE) + ",\n" + " " + INFO_KEY_UPLOADED_TIME + ": " + (this.getUploadedTime() != null ? this.getUploadedTime() : INFO_EMPTY_VALUE) + '\n' + "}";
    }
}
