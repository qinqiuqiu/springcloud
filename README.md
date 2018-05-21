# Spring Cloud  学习及模块说明                 
## eureka_server 服务端模块
     spring-cloud eureka server 开启口令认证支持;
     * pom.xml添加依赖
         <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
         </dependency>

     * application配置
         security:
            basic:
               enabled: true
         #enreka server 后台支持口令认证
         eureka:
            client:
                service-url:
                    defaultZone: http://qinqiuqiu:123@localhost:8761/eureka 

## provider_user用户微服务模块 eureka client
    给微服务定义名称及使用VIP地址访问
        spring:
          application:
            name: provider-user-service
        
        eureka:
          instance:
            prefer-ip-address: true
            instance-id: ${spring.application.name}:${spring.application.instance_id:${server.port}}    


## consumer_movie_ribbon电影微服务模块 eureka client
    微服务 RestTemplate spring cloud restful API
```
    @Bean
    /**
     * 开启客户端的负载均衡; (去Eureka Server端拉取微服务VIP[virtual IP; spring.application.name] 列表; 
        访问该客户端接口时，执行Ribbon的负载均衡算法; 默认为轮询算法)
     */
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
```
    访问用户微服务provider_user  http://host:port/user/hi; 这样存在用户微服务IP或port发生变化后
        ，需要修改对应的IP或port;

    1. 解决方案
        application配置
            spring:
                application:
                    name: provider-user-service
            eureka:
                client:
                    instance:
                        prefer-ip-address:  true
                        instance-id: ${spring.application.name}:${spring.application.instance_id:${server.port}}

    2. 微服务监控
        application配置
            eureka.client.ealthcheck.enabled=true
        pom.xml添加依赖
            <!-- 服务监控 -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-actuator</artifactId>
            </dependency>

## consumer_movie_ribbon_ext1模块 自定义Ribbon负载均衡算法
    1.不在同一个包中：
        org.troy.config.MyRibbonLoadBalance; #自定义Ribbon算法
        org.troy.ribbon.ConsumerMovieRibbonExt1Application  # SpringBoot 启动类
        注意：在SpringBoot Application启动类@ComponentScan扫描不到MyRibbonLoadBalance

    2.如果在同一个包中：
        MyRibbonLoadBalance随机负载均衡算法就会作为全局的负载均衡算法(覆盖默认负载均衡算法);
        默认全局负载均衡算法为轮询。

        如果写自定义负载均衡算法与启动类在同一个包下；但不想覆盖默认负载均衡算法，可通过自定义注解来解决:
            启动类上添加注解代码:
                @ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION
                    , value = ExcludeFooComponentScan.class) }) #ExcludeFooComponentScan为自定义注解

            自定义Ribbon算法类上添加自定义注解@ExcludeFooComponentScan

## consumer_movie_ribbon_ext2模块 定自义Ribbon负载均衡算法
    在application配置文件中配置 # serviceId为访问微服务的服务名(spring.application.name)
        serviceId:
            ribbon:
                NFLoadBalancerRuleClassName: com.netflix.loadbalance.RandomRule 

## consumer_movie_ribbon_without_eureka模块
    * ribbon 不依赖eureka的注册中心微服务列表
         application配置
            #配置文件中访问微服务provider-user-service; 使用随机负载均衡算法
            provider-user-service:
                ribbon:
                  listOfServers: localhost:7001,localhost:7000 #设置ribbon访问的微服务地址
            
            #ribbon 禁止使用eureka(获取eureka拉取服务列表)
            ribbon:
              eureka:
                enabled: false
                
## consumer_movie_feign 模块
    1. 启动类上添加 @EnableFeignClients
    
    2. 编写声明式 web client service 接口
```Java
    @FeignClient("provider-user-service")  //指定访问微服务接口的serviceId
    public interface UserFeignClient {
    
        @RequestMapping(method = RequestMethod.GET, value = "/hi")
        Object hello();
    }
```