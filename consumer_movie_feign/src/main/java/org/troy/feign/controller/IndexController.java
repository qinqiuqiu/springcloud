package org.troy.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/movie")
    public Object consumer(){
        return userFeignClient.hello();
    }

    @GetMapping("/test-post")
    public Object doPost(){
        return userFeignClient.doPost("{\"code\":0,\"message\":\"..........\"}");
    }
}
