package com.xiaoyong.hrm;/**
 * Created by atlantisholic on 2018/8/2.
 */

import com.xiaoyong.hrm.support.domain.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @ClassName HrmSpringBootApplication
 * @Description TODO
 * @Author 郁晓勇
 * @Date 2018/8/2 20:53
 * @Version 1.0.0
 **/
//@EnableLoadTimeWeaving
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EnableJpaAuditing
@EnableCaching
@SpringBootApplication
public class CrmSpringBootApplication extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CrmSpringBootApplication.class);
    }

    public static void main(String[] args) throws Exception {
//        Set<Field> fields = ReflectionUtils.getAllFields(SysMenu.class, ReflectionUtils.withType(String.class));
//        fields.forEach((field) -> {
//            System.out.println(field);
//        });
//        Set<Method> methods = ReflectionUtils.getAllMethods(CustomerFollow.class, ReflectionUtils.withAnnotation(OneToMany.class));
//        methods.forEach( method -> {
//            ParameterizedTypeImpl type = (ParameterizedTypeImpl)method.getGenericReturnType();
//            Class clazz = (Class)type.getActualTypeArguments()[0];
//            System.out.println(clazz);
//            Set<Method> methods1 = ReflectionUtils.getMethods(clazz, ReflectionUtils.withPrefix("set"), ReflectionUtils.withParameters(CustomerFollow.class));
//            methods1.forEach( method1 -> {
//                System.out.println(method1);
//            });
//        });
        SpringApplication.run(CrmSpringBootApplication.class, args);
    }

}
