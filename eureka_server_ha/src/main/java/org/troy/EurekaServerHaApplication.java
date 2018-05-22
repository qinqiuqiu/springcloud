package org.troy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  // 开启Eureka Server
public class EurekaServerHaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerHaApplication.class, args);
	}
}
