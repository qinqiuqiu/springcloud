package org.troy.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.troy.config.EurekaConfiguration;

/**
 * @title
 * @Author 覃球球
 * @Version 1.0 on 2018/5/21.
 * @Copyright 长沙科权
 */
@FeignClient(name = "eurekaServer", url = "http://localhost:8761/", configuration = EurekaConfiguration.class)
public interface EurekaServerFeignClient {

     @GetMapping("/eureka/apps/{serviceName}")
     String findServiceNameByEurekaServer(@PathVariable("serviceName") String serviceName);
}
