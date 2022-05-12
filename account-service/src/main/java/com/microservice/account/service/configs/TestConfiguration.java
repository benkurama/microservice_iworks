package com.microservice.account.service.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class TestConfiguration {
    @Value("${developer.name}")
    private String namae;

    // not working with profiles
    @Value("${profile.name}")
    private String pname;

    public String getNamae(){
        return namae;
    }

    public String getProfile(){
        return pname;
    }
}
