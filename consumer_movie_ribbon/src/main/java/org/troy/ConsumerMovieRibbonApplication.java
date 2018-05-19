package org.troy;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient  // 开启Eureka Client
public class ConsumerMovieRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieRibbonApplication.class, args);
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
