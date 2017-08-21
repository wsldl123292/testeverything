package org.smart4j.framework.core.ds;

import javax.sql.DataSource;

/**
 * 功能:
 * 作者: ldl
 * 时间: 2017-08-21 22:08
 */
public interface DataSourceFactory {

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    DataSource getDataSource();
}
