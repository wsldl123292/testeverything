package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.mvc.ActionHelper;
import org.smart4j.framework.mvc.Handler;
import org.smart4j.framework.mvc.HandlerMapping;
import org.smart4j.framework.mvc.Requester;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:37
 */
public class DefaultHandlerMapping implements HandlerMapping {
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
        for (Map.Entry<Requester, Handler> actionEntry : actionMap.entrySet()) {
            Requester requester = actionEntry.getKey();
            String requestMethod = requester.getRequestMethod();
            String requestPath = requester.getRequestPath();
            // 获取请求路径匹配器（使用正则表达式匹配请求路径并从中获取相应的请求参数）
            Matcher requestPathMatcher = Pattern.compile(requestPath).matcher(currentRequestPath);
            // 判断请求方法与请求路径是否同时匹配
            if (requestMethod.equalsIgnoreCase(currentRequestMethod) && requestPathMatcher.matches()) {
                // 获取 Handler 及其相关属性
                handler = actionEntry.getValue();
                // 设置请求路径匹配器
                if (handler != null) {
                    handler.setRequestPathMatcher(requestPathMatcher);
                }
                // 若成功匹配，则终止循环
                break;
            }
        }
        return handler;
    }
}
