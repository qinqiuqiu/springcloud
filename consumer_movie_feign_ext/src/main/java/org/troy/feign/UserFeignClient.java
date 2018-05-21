package org.troy.feign;

import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.troy.config.ProviderUserConfiguration;

/**
 * @title  Feign Client
 * @Author 覃球球
 * @Version 1.0 on 2018/5/21.
 * @Copyright 长沙科权
 */
@FeignClient(name = "provider-user-service", configuration = ProviderUserConfiguration.class)
public interface UserFeignClient {

//    @RequestMapping(method = RequestMethod.GET, value = "/hi")
    @RequestLine("GET /hi")
    Object hello();

//    @RequestMapping(method = RequestMethod.POST, value = "/post")
    @RequestLine("POST /post")
    Object doPost(@RequestBody String data);
}
