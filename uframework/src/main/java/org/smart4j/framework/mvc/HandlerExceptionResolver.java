package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/24 16:04
 */
public interface HandlerExceptionResolver {
    /**
     * 解析 Handler 异常
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param e        异常
     */
    void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e);
}

