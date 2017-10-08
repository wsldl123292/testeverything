package org.smart4j.framework.mvc.fault;

/**
 * @描述 上传异常（当文件上传失败时抛出）
 * @作者 liudelin
 * @日期 2017/9/19 11:10
 */
public class UploadException extends RuntimeException {

    public UploadException() {
        super();
    }

    public UploadException(String message) {
        super(message);
    }

    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadException(Throwable cause) {
        super(cause);
    }
}
