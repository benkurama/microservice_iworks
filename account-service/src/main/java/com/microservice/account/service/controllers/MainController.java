package com.microservice.account.service.controllers;


import com.microservice.account.service.configs.DataSourceConfig;
import com.microservice.account.service.configs.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    TestConfiguration testConfiguration;

/*    @Autowired
    DataSourceConfig dataSourceConfig;*/

    @GetMapping("/showPort")
    private String showPort(){
        return port;
    }

    @GetMapping("/show")
    private String show(){

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(testConfiguration.getNamae());
        stringBuilder.append(" and ");
        stringBuilder.append(testConfiguration.getProfile());

        return stringBuilder.toString();
    }

    @GetMapping("/reconfigure")
    private String reconfigure(){
        //dataSourceConfig.getDatasource();
        return "successfully configure";
    }

  /*@GetMapping("/showMain")
    @HystrixCommand(fallbackMethod = "getDefault",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value="60")
            }
    )
    private ResponseEntity<String> showMain(){

        return new ResponseEntity("It Works", HttpStatus.OK);
    }

    @SuppressWarnings("unused")
    ResponseEntity<String> getDefault() {

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }*/

}
