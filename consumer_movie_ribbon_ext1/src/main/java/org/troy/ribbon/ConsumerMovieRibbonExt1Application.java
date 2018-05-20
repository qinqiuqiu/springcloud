package org.troy.ribbon;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.troy.config.MyRibbonLoadBalance;

@SpringBootApplication
@EnableEurekaClient  // 开启Eureka Client
//provider-user-service微服务使用MyRibbonLoadBalance 随机负载均衡算法; 其它微服务使用默认Ribbon轮询负载均衡算法
@RibbonClient(value = "provider-user-service", configuration = MyRibbonLoadBalance.class)
public class ConsumerMovieRibbonExt1Application {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonExt1Application.class, args);
	}

	@Bean
	/**
	 * 开启客户端的负载均衡; (去Eureka Server端拉取微服务VIP[virtual IP; spring.application.name] 列表; 访问该客户端接口时，
	 * 	执行Ribbon的负载均衡算法; 默认为轮询算法)
     */
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
