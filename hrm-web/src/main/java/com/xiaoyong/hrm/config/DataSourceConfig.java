package com.xiaoyong.hrm.config;/**
 * Created by atlantisholic on 2018/5/25.
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/5/25 15:51
 * @Version 1.0.0
 **/
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.dashboard.allow}")
    private String accessAllow;
    @Value("${spring.datasource.dashboard.deny}")
    private String accessDeny;
    @Value("${spring.datasource.dashboard.username}")
    private String accessUsername;
    @Value("${spring.datasource.dashboard.password}")
    private String accessPassword;
    @Value("${spring.datasource.dashboard.resetEnable}")
    private String resetEnable;

    @Bean(destroyMethod = "close", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean druidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //白名单：
        servletRegistrationBean.addInitParameter("allow", accessAllow);
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny", accessDeny);
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", accessUsername);
        servletRegistrationBean.addInitParameter("loginPassword", accessPassword);
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", resetEnable);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean druidStatFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
