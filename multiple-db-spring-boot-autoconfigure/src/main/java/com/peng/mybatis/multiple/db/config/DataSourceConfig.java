package com.peng.mybatis.multiple.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by fp295 on 2018/10/21.
 */

@Configuration
public class DataSourceConfig {

    //默认数据源
    @Bean(name = "defaultDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource defaultDatasource() {
        //return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    //数据源2
    /*@Bean(name = "datasource2")
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }*/

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public Properties datasourceProperties() {
        return new Properties();
    }


    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(defaultDatasource());
        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap();
        /*dsMap.put("datasource1", dataSource1());
        dsMap.put("datasource2", dataSource2());*/

        datasourceProperties().forEach((key, value) -> {
            Map ds = (Map)value;
            DruidDataSource dataSource = new DruidDataSource();
            //驱动类
            dataSource.setDriverClassName((String) ds.get("driver-class-name"));
            //数据库连接
            dataSource.setUrl((String) ds.get("url"));
            //用户名
            dataSource.setUsername((String) ds.get("username"));
            //密码
            dataSource.setPassword((String) ds.get("password"));

            DatabaseDriver databaseDriver = DatabaseDriver
                    .fromJdbcUrl((String) ds.get("url"));
            String validationQuery = databaseDriver.getValidationQuery();
            if (validationQuery != null) {
                dataSource.setTestOnBorrow(true);
                dataSource.setValidationQuery(validationQuery);
            }
            dsMap.put(key, dataSource);
        });
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     *
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
