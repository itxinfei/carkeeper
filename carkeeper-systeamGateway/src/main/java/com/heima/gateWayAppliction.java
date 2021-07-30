package com.heima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class gateWayAppliction {
    public static void main(String[] args) {
        SpringApplication.run (gateWayAppliction.class,args);
    }
}
