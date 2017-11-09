package org.smart4j.framework.mvc.bean;

import org.smart4j.framework.core.bean.BaseBean;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/11/9 16:04
 */
public class Result extends BaseBean {
    /**
     * 成功标志
     */
    private boolean success;
    /**
     * 错误代码
     */
    private int error;
    /**
     * 相关数据
     */
    private Object data;

    public Result(boolean success) {
        this.success = success;
    }

    public Result error(int error) {
        this.error = error;
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
