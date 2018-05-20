package org.troy.ribbon.controller;

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
    public Object consumer(){
        return restTemplate.getForObject("http://provider-user-service/hi", Map.class);
    }
}
