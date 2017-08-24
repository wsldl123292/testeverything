package org.smart4j.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/24 15:38
 */
public interface ViewResolver {

    /**
     * 解析视图
     *
     * @param request            请求对象
     * @param response           响应对象
     * @param actionMethodResult Action 方法返回值
     */
    void resolveView(HttpServletRequest request, HttpServletResponse response, Object actionMethodResult);
}

