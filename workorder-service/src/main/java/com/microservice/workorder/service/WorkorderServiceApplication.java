package com.microservice.workorder.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class WorkorderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkorderServiceApplication.class, args);
	}

}
