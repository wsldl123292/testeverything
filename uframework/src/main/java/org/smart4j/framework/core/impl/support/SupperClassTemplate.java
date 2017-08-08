package org.smart4j.framework.core.impl.support;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-08 22:50
 */
public abstract class SupperClassTemplate extends ClassTemplate {

    protected final Class<?> superClass;

    protected SupperClassTemplate(String packageName, Class<?> superClass) {
        super(packageName);
        this.superClass = superClass;
    }
}
