package com.peng.mybatis.multiple.db.example;

/**
 * Created by fp295 on 2018/10/21.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * springboot入口类,此类需要在所有用到的package上层
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ServletComponentScan("com.peng.mybatis.multiple")
//@MapperScan(basePackages = {"com.peng.mybatis.multiple.db.example.mapper.mapper"})
public class Application {
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

}