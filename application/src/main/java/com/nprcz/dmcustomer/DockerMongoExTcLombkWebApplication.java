package com.nprcz.dmcustomer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JWTConfigurationProperties.class)
public class DockerMongoExTcLombkWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(DockerMongoExTcLombkWebApplication.class, args);

    }
}
