package com.peng.mybatis.multiple.db.config;

/**
 * Created by fp295 on 2018/10/21.
 */
public class DataSourceContextHolder {
    /**
     * 默认数据源
     */
    public static final String DEFAULT_DS = "db1";

    private static final ThreadLocal<String> contextHolder = new ThreadLocal();

    // 设置数据源名
    public static void setDB(String dbType) {
        System.out.println("切换到{"+dbType+"}数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源名
    public static String getDB() {
        String ds;
        if((ds = contextHolder.get()) != null)
            return ds;
        return DEFAULT_DS;
    }

    // 清除数据源名
    public static void clearDB() {
        contextHolder.remove();
    }

}
