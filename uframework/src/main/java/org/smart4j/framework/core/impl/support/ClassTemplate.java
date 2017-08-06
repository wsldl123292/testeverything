package org.smart4j.framework.core.impl.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ClassTemplate {

    private static final Logger logger = LoggerFactory.getLogger(ClassTemplate.class);

    protected final String packageName;

    protected ClassTemplate(String packageName) {
        this.packageName = packageName;
    }
}
