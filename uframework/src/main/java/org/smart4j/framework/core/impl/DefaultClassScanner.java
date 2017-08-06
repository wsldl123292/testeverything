package org.smart4j.framework.core.impl;

import org.smart4j.framework.core.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.List;

public class DefaultClassScanner implements ClassScanner {
    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new classtemp;
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotationClass) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
