package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.mvc.Handler;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.Requester;

import java.util.Map;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:37
 */
public class DefaultHandlerMapping implements HandlerMapping{
    /**
     * 获取 Handler
     *
     * @param currentRequestMethod 当前请求方法
     * @param currentRequestPath   当前请求路径
     * @return Handler
     */
    @Override
    public Handler getHandler(String currentRequestMethod, String currentRequestPath) {

        Handler handler = null;
        Map<Requester, Handler> actionMap = ActionHelper.getActionMap();
        return handler;
    }
}
