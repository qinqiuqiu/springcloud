package org.troy.feign;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient  // 开启Eureka Client
@EnableFeignClients
public class ConsumerMovieFeignExtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerMovieFeignExtApplication.class, args);
	}
}