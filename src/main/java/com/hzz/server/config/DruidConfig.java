package com.hzz.server.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Autowired
    private DruidProperties properties;

    @Bean(name = "dataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(properties.getUrl());
        datasource.setDriverClassName(properties.getDriverClassName());
        datasource.setUsername(properties.getUsername());
        datasource.setPassword(properties.getPassword());

        // 获取连接等待超时的时间
        datasource.setMaxWait(properties.getMaxWait());
        // 初始化大小、最小、最大
        datasource.setInitialSize(properties.getInitialSize());
        datasource.setMinIdle(properties.getMinIdle());
        datasource.setMaxActive(properties.getMaxActive());

        datasource.setTestWhileIdle(properties.isTestWhileIdle());
        datasource.setValidationQuery(properties.getValidationQuery());
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);
        // 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        datasource.setTimeBetweenEvictionRunsMillis(60000);
        // 一个连接在池中最小生存的时间，单位是毫秒
        datasource.setMinEvictableIdleTimeMillis(300000);

        // 自动回收超时连接
        datasource.setRemoveAbandoned(true);
        // 超时时间(单位 秒)
        // 自动回收超时连接时打印连接的超时错误
        datasource.setLogAbandoned(true);

        // 慢查询
        datasource.setConnectionProperties(properties.getConnectionProperties());

        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // http://localhost:8080/druid/weburi.html
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername", "admin");  // 用户名
        initParameters.put("loginPassword", "123123");   // 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
//        initParameters.put("allow", "127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)
        // initParameters.put("deny", "192.168.20.38");// IP黑名单
        // (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    @Bean(value = "druid-stat-interceptor")
    public DruidStatInterceptor DruidStatInterceptor() {
        DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();
        return druidStatInterceptor;
    }

    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setProxyTargetClass(true);
        // 设置要监控的bean的id
        //beanNameAutoProxyCreator.setBeanNames("sysRoleMapper","loginController");
        beanNameAutoProxyCreator.setInterceptorNames("druid-stat-interceptor");
        return beanNameAutoProxyCreator;
    }

}