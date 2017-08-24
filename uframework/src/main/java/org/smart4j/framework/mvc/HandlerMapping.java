package org.smart4j.framework.mvc;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:33
 */
public interface HandlerMapping {

    /**
     * 获取 Handler
     *
     * @param currentRequestMethod 当前请求方法
     * @param currentRequestPath   当前请求路径
     * @return Handler
     */
    Handler getHandler(String currentRequestMethod, String currentRequestPath);
}
