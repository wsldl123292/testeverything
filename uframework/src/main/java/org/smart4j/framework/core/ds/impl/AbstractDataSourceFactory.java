package org.smart4j.framework.core.ds.impl;

import org.smart4j.framework.core.ConfigHelper;
import org.smart4j.framework.core.ds.DataSourceFactory;

import javax.sql.DataSource;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:11
 */
public abstract class AbstractDataSourceFactory<T extends DataSource> implements DataSourceFactory {
    protected final String driver = ConfigHelper.getString("smart.framework.jdbc.driver");
    protected final String url = ConfigHelper.getString("smart.framework.jdbc.url");
    protected final String username = ConfigHelper.getString("smart.framework.jdbc.username");
    protected final String password = ConfigHelper.getString("smart.framework.jdbc.password");


    @Override
    public T getDataSource() {
        T ds = createDataSource();
        return null;
    }

    public abstract T createDataSource();
}
