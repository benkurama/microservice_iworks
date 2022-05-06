package com.microservice.account.service.controllers;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by Default on 05/05/2022.
 */
@RestController
@RequestMapping("/main")
public class MainController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/showPort")

    private String showPort(){

        return port;
    }

    @GetMapping("/showMain")
    @HystrixCommand(fallbackMethod = "getDefault",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
            }
    )
    private Optional<String> showMain(){
        return Optional.ofNullable("Microservice : Acccounts");
    }

    @SuppressWarnings("unused")
    String getDefault() {

        return "Error Exception";
    }

}
