package org.smart4j.framework.plugin;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 15:25
 */
public interface Plugin {

    /**
     * 初始化插件
     */
    void init();

    /**
     * 销毁插件
     */
    void destroy();
}
