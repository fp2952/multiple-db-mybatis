package com.peng.mybatis.multiple.db.config.mapper;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by fp295 on 2018/4/7.
 * 通用mapper配置
 * */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(MapperProperties.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class MapperConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MapperProperties properties;

    @PostConstruct
    public void addPageInterceptor() {
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.setConfig(properties);
        //提前初始化MapperFactoryBean,注册mappedStatements
        applicationContext.getBeansOfType(Mapper.class);
        mapperHelper.registerMapper(Mapper.class);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            mapperHelper.processConfiguration(sqlSessionFactory.getConfiguration());
        }
    }
}
