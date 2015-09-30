package java;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: LDL
 * 功能说明:
 * 创建日期: 2015/9/21 17:25
 */
public class SparkJobResult extends SparkJobBaseInfo {
    private String result;
    private Map<String, Object> extendAttributes = new HashMap<>();

    SparkJobResult(String contents) {
        this.contents = contents;
    }

    public String getResult() {
        return result;
    }

    void setResult(String result) {
        this.result = result;
    }

    void putExtendAttribute(String key, Object value) {
        this.extendAttributes.put(key, value);
    }

    public Map<String, Object> getExtendAttributes() {
        return new HashMap<>(this.extendAttributes);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuffer buff = new StringBuffer("SparkJobResult\n");
        buff.append(contents);
        return buff.toString();
    }

    /**
     * Judges current <code>SparkJobResult</code> instance represents the
     * status information of a asynchronous running spark job or not.
     *
     * @return true indicates it contains asynchronous running status of a
     * spark job, false otherwise
     */
    public boolean containsAsynStatus() {
        return SparkJobBaseInfo.INFO_STATUS_STARTED.equals(getStatus())
                && getJobId() != null && getContext() != null;
    }

    /**
     * Judges the queried target job doesn't exist or not.
     *
     * @return true indicates the related job doesn't exist, false otherwise
     */
    public boolean jobNotExists() {
        return SparkJobBaseInfo.INFO_STATUS_ERROR.equals(getStatus()) &&
                getResult() != null && getResult().contains("No such job ID");
    }

    /**
     * Judges current <code>SparkJobResult</code> instance contains
     * error information of a failed spark job or not.
     *
     * @return true indicates it contains error message, false otherwise
     */
    public boolean containsErrorInfo() {
        return SparkJobBaseInfo.INFO_STATUS_ERROR.equals(getStatus()) &&
                getMessage() != null;
    }

    /**
     * Judges current <code>SparkJobResult</code> instance contains
     * custom-defined extend attributes of result or not
     *
     * @return true indicates it contains custom-defined extend attributes, false otherwise
     */
    public boolean containsExtendAttributes() {
        return !extendAttributes.isEmpty();
    }
}
