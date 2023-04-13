package com.mingfahk.chatgpt.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {

    //读取刚刚配置的数据源的信息到方法dataSource()的返回值 --> DruidDataSource()中，实现属性注入。
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}
