package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.configs.TestConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @Autowired
    TestConfiguration testConfiguration;

    @GetMapping("/show")
    private String show(){
        return testConfiguration.getNamae();
    }
}
