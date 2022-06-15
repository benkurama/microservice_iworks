package com.gateway.edge.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableDiscoveryClient
@EnableFeignClients

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EdgeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServiceApplication.class, args);
	}

/*	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("*")
						.allowedOrigins("https://www.aliwork.com","https://uczkrh.aliwork.com")
						.allowCredentials(true);
			}
		};
	}*/
}
