package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.InstanceFactory;
import org.smart4j.framework.core.BeanHelper;
import org.smart4j.framework.mvc.Handler;
import org.smart4j.framework.mvc.HandlerInvoker;
import org.smart4j.framework.mvc.UploadHelper;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.bean.Params;
import org.smart4j.framework.util.MapUtil;
import org.smart4j.framework.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/24 15:36
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = InstanceFactory.getViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception {
        Class<?> actionClass = handler.getActionClass();
        Method actionMethod = handler.getActionMethod();

        Object actionInstance = BeanHelper.getBean(actionClass);
        List<Object> actionMethodParamList = createActionMethodParamList(request, handler);
    }


    public List<Object> createActionMethodParamList(HttpServletRequest request, Handler handler) throws Exception {
        // 定义参数列表
        List<Object> paramList = new ArrayList<>();
        // 获取 Action 方法参数类型
        Class<?>[] actionParamTypes = handler.getActionMethod().getParameterTypes();
        // 添加路径参数列表（请求路径中的带占位符参数）
        paramList.addAll(createPathParamList(handler.getRequestPathMatcher(), actionParamTypes));
        // 分两种情况进行处理
        if (UploadHelper.isMultipart(request)) {
            // 添加 Multipart 请求参数列表
            paramList.addAll(UploadHelper.createMultipartParamList(request));
        } else {
            // 添加普通请求参数列表（包括 Query String 与 Form Data）
            Map<String, Object> requestParamMap = WebUtil.getRequestParamMap(request);
            if (MapUtil.isNotEmpty(requestParamMap)) {
                paramList.add(new Params(requestParamMap));
            }
        }
        // 返回参数列表
        return paramList;
    }
}
