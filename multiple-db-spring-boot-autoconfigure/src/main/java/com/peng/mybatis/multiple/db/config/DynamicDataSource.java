package com.peng.mybatis.multiple.db.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by fp295 on 2018/10/21.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dateSource = DataSourceContextHolder.getDB();
        System.out.println("数据源为"+dateSource);
        return dateSource;
    }

}
