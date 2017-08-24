package org.smart4j.framework.ds.impl;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:25
 */
public class DefaultDataSourceFactory extends AbstractDataSourceFactory<BasicDataSource> {
    @Override
    public BasicDataSource createDataSource() {
        return new BasicDataSource();
    }
}
