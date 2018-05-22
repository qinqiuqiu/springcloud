package org.troy.feign.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @title
 * @Author 覃球球
 * @Version 1.0 on 2018/5/19.
 * @Copyright 湖南科权
 */
@RestController
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/movie")
    @HystrixCommand(fallbackMethod = "consumerHystrix")  //断路器 fallback method
    public Object consumer(){
        return restTemplate.getForObject("http://provider-user-service/hi", Map.class);
    }


    /**
     * 目标方法不可访问时，调用该fallback method
     * @return
     */
    public Object consumerHystrix(){
        return "{\"none\":0}";
    }
}
