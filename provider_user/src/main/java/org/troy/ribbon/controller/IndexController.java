package org.troy.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    private Environment env;

    @GetMapping("/hi")
    public Object hi(){
        System.out.println("hi， I am " + env.getProperty("spring.application.name")
                + "  port : " + env.getProperty("server.port"));

        Map<String, String> maps = new HashMap<String, String>();
        maps.put("name", env.getProperty("spring.application.name"));
        maps.put("port", env.getProperty("server.port"));
        return maps;
    }
}
