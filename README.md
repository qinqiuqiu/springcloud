########################################################################################################################
##################################  Spring Cloud  学习及模块说明                  #######################################
########################################################################################################################
eureka_server 服务端模块
     spring-cloud eureka server 开启口令认证支持;需要在pom.xml添加依赖
     <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
     </dependency>

     application配置
        security.basic.enabled=true
        eureka.client.service-url.defaultZone=http://qinqiuqiu:123@localhost:8761/eureka #enreka server 后台支持口令认证

provider_user 用户微服务 eureka client


consumer_movie_ribbon 电影微服务 eureka client
    RestTemplate spring cloud restful API

    @Bean
    /**
     * 开启客户端的负载均衡; (去Eureka Server端拉取微服务VIP[virtual IP; spring.application.name] 列表; 访问该客户端接口时，
     * 	执行Ribbon的负载均衡算法; 默认为轮询算法)
     */
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    访问用户微服务provider_user  http://host:port/user/hi; 这样存在用户微服务IP或port发生变化后，需要修改对应的IP或port;

    解决方案
        application配置
            spring.application.name=provider-user-service
            eureka.client.instance.prefer-ip-address=true
            eureka.client.instance.instance-id=${spring.application.name}:${spring.application.instance_id:${server.port}}

    微服务监控
        application配置
            eureka.client.ealthcheck.enabled=true
        pom.xml添加依赖
            <!-- 服务监控 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>