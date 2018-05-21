package org.troy.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/movie")
    public Object consumer(){
        return restTemplate.getForObject("http://provider-user-service/hi", Map.class);
    }

    @GetMapping("/lb")
    public Object getLoadBalance(){
        ServiceInstance s1 = loadBalancerClient.choose("provider-user-service");

        ServiceInstance s2 = loadBalancerClient.choose("provider-user-service2");

        Map<String, String> map = new HashMap<String, String>();
        map.put(s1.getServiceId(), s1.getHost() + ":" + s1.getPort());
        map.put(s2.getServiceId(), s2.getHost() + ":" + s2.getPort());

        System.out.println("map ======================> " + map);
        return map;
    }
}
