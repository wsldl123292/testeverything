package org.smart4j.framework.plugin;

import javax.servlet.ServletContext;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 15:28
 */
public abstract class WebPlugin implements Plugin {
    @Override
    public void init() {
    }

    @Override
    public void destroy() {
    }

    public abstract void register(ServletContext servletContext);
}
