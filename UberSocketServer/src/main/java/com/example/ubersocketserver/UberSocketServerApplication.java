package com.example.ubersocketserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.example.uberprojectentityservice.models")
@SpringBootApplication
@EnableAutoConfiguration(excludeName = {"org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration"})
public class UberSocketServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberSocketServerApplication.class, args);
    }
}