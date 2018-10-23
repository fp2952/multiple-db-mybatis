package com.peng.mybatis.multiple.db.config.druid;


import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * /druid/*监控
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*",
        initParams={
                //@WebInitParam(name="allow",value="127.0.0.1"),// IP白名单 (没有配置或者为空，则允许所有访问)
                @WebInitParam(name="loginUsername",value="sysadmin"),// 用户名
                @WebInitParam(name="loginPassword",value="000000"),// 密码
                @WebInitParam(name="resetEnable",value="false")// 禁用HTML页面上的“Reset All”功能
        })
@Configuration
public class DruidStatViewServlet extends StatViewServlet {

}
