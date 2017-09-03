package org.smart4j.framework.mvc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.mvc.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/24 16:03
 */
public class DefaultHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger logger = LoggerFactory.getLogger(DefaultHandlerExceptionResolver.class);

    @Override
    public void resolveHandlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {

    }
}
