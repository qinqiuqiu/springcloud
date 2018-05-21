package org.troy.feign.controller;

import feign.Param;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.troy.feign.EurekaServerFeignClient;
import org.troy.feign.UserFeignClient;

/**
 * @title
 * @Author 覃球球
 * @Version 1.0 on 2018/5/19.
 * @Copyright 湖南科权
 */
@RestController
public class IndexController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private EurekaServerFeignClient eurekaServerFeignClient;

    @GetMapping("/movie")
    public Object consumer(){
        return userFeignClient.hello();
    }

    @GetMapping("/test-post")
    public Object doPost(){
        return userFeignClient.doPost("{\"code\":0,\"message\":\"..........\"}");
    }

    @GetMapping("/feign/{serviceName}")
    public String findServiceName(@PathVariable("serviceName") String serviceName){
        return eurekaServerFeignClient.findServiceNameByEurekaServer(serviceName);
    }
}
