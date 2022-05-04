package com.frontend.ui.services;

import com.frontend.ui.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Default on 26/04/2022.
 */
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