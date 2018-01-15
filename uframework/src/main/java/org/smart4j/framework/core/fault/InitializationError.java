package org.smart4j.framework.core.fault;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 15:50
 */
public class InitializationError extends Error {

    public InitializationError() {
        super();
    }

    public InitializationError(String message) {
        super(message);
    }

    public InitializationError(String message, Throwable cause) {
        super(message, cause);
    }

    public InitializationError(Throwable cause) {
        super(cause);
    }
}
