package com.xiaoyong.hrm.config;

import com.xiaoyong.common.xss.XssFilter;
import com.xiaoyong.hrm.support.web.JsonDateDeserializer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by atlantisholic on 2018/5/14.
 */
@Configuration
@EnableAsync
@EnableWebMvc
@ImportResource(locations = {"classpath:spring-context-shiro.xml"})
public class WebApplicationConfig extends WebMvcConfigurationSupport {

//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//
//        super.addResourceHandlers(registry);
//    }


    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean xssFilter = new FilterRegistrationBean();
        xssFilter.addUrlPatterns("/*");
        xssFilter.setFilter(new XssFilter());
        return xssFilter;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
                registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .indentOutput(true)
                .deserializerByType(Date.class, new JsonDateDeserializer());
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        //converters.add(new MappingJackson2XmlHttpMessageConverter(builder.xml().build()));
        super.configureMessageConverters(converters);
    }


//    @Bean
//    public ErrorPageRegistrar errorPageRegistrar(){
//        return new ErrorPageRegistrar() {
//            @Override
//            public void registerErrorPages(ErrorPageRegistry registry) {
//                registry.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
//                registry.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
//                registry.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
//                registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
//                registry.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
//            }
//        };
//    }


}
