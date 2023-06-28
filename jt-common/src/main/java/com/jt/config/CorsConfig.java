package com.jt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 是springMVC中的配置对象
 * Spring MVC 中的配置对象(此对象会在spring容器启动时初始化)
 * 可通过实现addInterceptors方法，注册一个拦截器
 *
 * Access-Control-Allow-Origin：允许哪些域来访问（*表示允许所有域的请求）
 * Access-Control-Allow-Methods：允许哪些请求方式
 * Access-Control-Allow-Headers：允许哪些请求头字段
 * Access-Control-Allow-Credentials：是否允许携带Cookie
 */
@Configuration  //标识我是一个配置类
public class CorsConfig implements WebMvcConfigurer {

    //在后端 配置cors允许访问的策略
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //允许访问后端任意的方法
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") //定义允许跨域的请求类型
                .allowedOrigins("*")           //任意网址都可以访问
                .allowCredentials(true) //是否允许携带cookie
                .maxAge(1800);                 //设定请求长链接超时时间.
    }
}
