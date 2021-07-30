package com.heima.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.heima.feigns")
public class HeimaFeignAutoConfiguration {
}