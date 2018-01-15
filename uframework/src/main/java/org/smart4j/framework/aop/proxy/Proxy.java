package org.smart4j.framework.aop.proxy;

/**
 * @描述
 * @作者 liudelin
 * @日期 2017/12/11 11:21
 */
public interface Proxy {

    /**
     * 执行链式代理
     *
     * @param proxyChain 代理链
     * @return 目标方法返回值
     * @throws Throwable 异常
     */
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
