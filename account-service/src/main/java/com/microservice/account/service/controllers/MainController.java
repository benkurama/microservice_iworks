package com.microservice.account.service.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
