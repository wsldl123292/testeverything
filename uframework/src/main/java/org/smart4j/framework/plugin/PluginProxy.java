package org.smart4j.framework.plugin;

import org.smart4j.framework.aop.proxy.Proxy;

import java.util.List;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 15:27
 */
public abstract class PluginProxy implements Proxy {
    public abstract List<Class<?>> getTargetClassList();
}
