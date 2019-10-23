package com.ctoader.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SimpleClient {

    public static void main(String[] args) {
        SpringApplication.run(SimpleClient.class);
    }
}
