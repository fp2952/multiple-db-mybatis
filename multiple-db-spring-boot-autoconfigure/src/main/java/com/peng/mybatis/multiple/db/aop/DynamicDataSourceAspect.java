package com.peng.mybatis.multiple.db.aop;

import com.peng.mybatis.multiple.db.config.DS;
import com.peng.mybatis.multiple.db.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by fp295 on 2018/10/21.
 * 自定义注解 + AOP的方式实现数据源动态切换。
 */
@Aspect
public class DynamicDataSourceAspect {

    @Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point, DS DS) {
        //获得当前访问的class
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        //得到方法的参数的类型
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = DataSourceContextHolder.DEFAULT_DS;
        try {
            // 得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            // 判断是否存在@DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                // 取出注解中的数据源名
                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 切换数据源
        DataSourceContextHolder.setDB(dataSource);
    }

    @After("@annotation(DS)")
    public void afterSwitchDS(JoinPoint point, DS DS) {
        DataSourceContextHolder.clearDB();
    }

}
