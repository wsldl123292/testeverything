package org.smart4j.framework.core;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.aop.annotation.Aspect;
import org.smart4j.framework.helper.ClassHelper;
import org.smart4j.framework.ioc.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanHelper {

    /**
     * Bean Map（Bean 类 => Bean 实例）
     */
    private static final Map<Class<?>, Object> beanMap = new HashMap<>();

    static {
        // 获取应用包路径下所有的类
        List<Class<?>> classList = ClassHelper.getClassList();
        for (Class<?> cls : classList) {
            // 处理带有 Bean/Service/Action/Aspect 注解的类
            if (cls.isAnnotationPresent(Bean.class) ||
                    cls.isAnnotationPresent(Service.class) ||
                    cls.isAnnotationPresent(Action.class) ||
                    cls.isAnnotationPresent(Aspect.class)) {
                // 创建 Bean 实例
                Object beanInstance = null;
                try {
                    beanInstance = cls.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                // 将 Bean 实例放入 Bean Map 中（键为 Bean 类，值为 Bean 实例）
                beanMap.put(cls, beanInstance);
            }
        }
    }
}
