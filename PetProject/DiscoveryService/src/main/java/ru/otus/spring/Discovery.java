package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
class Discovery {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Discovery.class,args);
    }
}
