package org.smart4j.framework.core;

import java.lang.annotation.Annotation;
import java.util.List;

public interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中指定注解的相关类
     */
    List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass);

    /**
     * 获取指定包名中指定父类或接口的相关类
     */
    List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass);
}
