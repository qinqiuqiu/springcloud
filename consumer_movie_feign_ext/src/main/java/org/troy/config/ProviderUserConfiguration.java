package org.troy.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title  自定义Feign 契约
 * @Author 覃球球
 * @Version 1.0 on 2018/5/21.
 * @Copyright 长沙科权
 */
@Configuration
public class ProviderUserConfiguration {

    /**
     * 定义 Feign 支持的契约 (默认为SpringMvcContract, rest 访问支持 spring mvc 注解 )
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    /**
     * Feign Client 日志
     * @return
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
