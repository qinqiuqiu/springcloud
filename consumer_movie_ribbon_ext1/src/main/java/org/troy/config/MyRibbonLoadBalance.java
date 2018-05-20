package org.troy.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @title  自定义Ribbon 负载均衡算法
 * @Author 覃球球
 * @Version 1.0 on 2018/5/19.
 * @Copyright 湖南科权
 */
@Configuration
public class MyRibbonLoadBalance {

    /**
     * 随机负载均衡算法
     * @return
     */
    @Bean
    public IRule createRule(){
        return new RandomRule();
    }
}
