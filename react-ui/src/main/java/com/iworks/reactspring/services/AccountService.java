package com.iworks.reactspring.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name="account-service",url="http://localhost:8090", path="/account/acct")
@Service
public interface AccountService {

    @GetMapping(value = "/showAll")
    public List<Map> showAllAccounts();

    @GetMapping(value = "/findByUsername")
    public Map findByUsername(@RequestParam(name="username") String username);

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();
}