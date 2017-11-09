package org.smart4j.framework.mvc.impl;

import org.smart4j.framework.InstanceFactory;
import org.smart4j.framework.core.BeanHelper;
import org.smart4j.framework.mvc.Handler;
import org.smart4j.framework.mvc.HandlerInvoker;
import org.smart4j.framework.mvc.UploadHelper;
import org.smart4j.framework.mvc.ViewResolver;
import org.smart4j.framework.mvc.bean.Params;
import org.smart4j.framework.util.CastUtil;
import org.smart4j.framework.util.ClassUtil;
import org.smart4j.framework.util.MapUtil;
import org.smart4j.framework.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/8/24 15:36
 */
public class DefaultHandlerInvoker implements HandlerInvoker {

    private ViewResolver viewResolver = InstanceFactory.getViewResolver();

    @Override
    public void invokeHandler(HttpServletRequest request, HttpServletResponse response, Handler handler) throws Exception {
        // 获取 Action 相关信息
        Class<?> actionClass = handler.getActionClass();
        Method actionMethod = handler.getActionMethod();
        // 从 BeanHelper 中创建 Action 实例
        Object actionInstance = BeanHelper.getBean(actionClass);
        // 创建 Action 方法的参数列表
        List<Object> actionMethodParamList = createActionMethodParamList(request, handler);
        // 检查参数列表是否合法
        checkParamList(actionMethod, actionMethodParamList);
        // 调用 Action 方法
        Object actionMethodResult = invokeActionMethod(actionMethod, actionInstance, actionMethodParamList);
        // 解析视图
        viewResolver.resolveView(request, response, actionMethodResult);
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

    private List<Object> createPathParamList(Matcher requestPathMatcher, Class<?>[] actionParamTypes) {
        //定义参数列表
        List<Object> paramList = new ArrayList<>();
        for (int i = 0; i < requestPathMatcher.groupCount(); i++) {
            String param = requestPathMatcher.group(i);
            Class<?> paramType = actionParamTypes[i - 1];
            if (ClassUtil.isInt(paramType)) {
                paramList.add(CastUtil.castInt(param));
            } else if (ClassUtil.isLong(paramType)) {
                paramList.add(CastUtil.castLong(param));
            } else if (ClassUtil.isDouble(paramType)) {
                paramList.add(CastUtil.castDouble(param));
            } else if (ClassUtil.isString(paramType)) {
                paramList.add(param);
            }
        }
        return paramList;
    }

    private Object invokeActionMethod(Method actionMethod, Object actionInstance, List<Object> actionMethodParamList) throws IllegalAccessException, InvocationTargetException {
        // 通过反射调用 Action 方法 取消类型安全检测（可提高反射性能）
        actionMethod.setAccessible(true);
        return actionMethod.invoke(actionInstance, actionMethodParamList.toArray());
    }

    private void checkParamList(Method actionMethod, List<Object> actionMethodParamList) {
        // 判断 Action 方法参数的个数是否匹配
        Class<?>[] actionMethodParameterTypes = actionMethod.getParameterTypes();
        if (actionMethodParameterTypes.length != actionMethodParamList.size()) {
            String message = String.format("因为参数个数不匹配，所以无法调用 Action 方法！原始参数个数：%d，实际参数个数：%d", actionMethodParameterTypes.length, actionMethodParamList.size());
            throw new RuntimeException(message);
        }
    }
}
