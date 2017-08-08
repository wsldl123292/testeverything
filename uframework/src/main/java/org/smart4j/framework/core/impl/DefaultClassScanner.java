package org.smart4j.framework.core.impl;

import org.smart4j.framework.core.ClassScanner;
import org.smart4j.framework.core.impl.support.AnnotationClassTemplate;
import org.smart4j.framework.core.impl.support.ClassTemplate;
import org.smart4j.framework.core.impl.support.SupperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

public class DefaultClassScanner implements ClassScanner {
    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new ClassTemplate(packageName) {

            /**
             * 验证是否允许添加类
             *
             * @param cls
             */
            @Override
            public boolean checkAddClass(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0, className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return new AnnotationClassTemplate(packageName, annotationClass) {

            /**
             * 验证是否允许添加类
             *
             * @param cls
             */
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return new SupperClassTemplate(packageName, superClass) {

            /**
             * 验证是否允许添加类
             *
             * @param cls
             */
            @Override
            public boolean checkAddClass(Class<?> cls) {
                return superClass.isAssignableFrom(cls) && !superClass.equals(cls);
            }
        }.getClassList();
    }
}
