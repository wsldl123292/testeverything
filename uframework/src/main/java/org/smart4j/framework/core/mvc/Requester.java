package org.smart4j.framework.core.mvc;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:39
 */
public class Requester {
    private String requestMethod;
    private String requestPath;

    public Requester(String requestMethod, String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }
}
