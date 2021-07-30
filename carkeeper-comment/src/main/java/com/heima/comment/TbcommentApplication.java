package com.heima.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient//nos
//@ServletComponentScan//spring容器
//@EnableFeignClients//feign
public class TbcommentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TbcommentApplication.class,args);
    }

}
