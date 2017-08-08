package org.smart4j.framework.core.impl.support;

import java.lang.annotation.Annotation;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-08 22:45
 */
public abstract class AnnotationClassTemplate extends ClassTemplate {

    protected final Class<? extends Annotation> annotationClass;

    protected AnnotationClassTemplate(String packageName, Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotationClass = annotationClass;
    }
}
