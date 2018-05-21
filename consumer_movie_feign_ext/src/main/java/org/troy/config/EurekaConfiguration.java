package org.troy.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title  自定义Feign 契约
 * @Author 覃球球
 * @Version 1.0 on 2018/5/21.
 * @Copyright 长沙科权
 */
@Configuration
public class EurekaConfiguration {

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("qinqiuqiu", "123");
    }
}
