package com.gateway.edge.service.services;

import com.gateway.edge.service.config.FeignConfiguration;
import com.gateway.edge.service.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * Created by Default on 22/04/2022.
 */
@FeignClient(name="account-service",url="http://localhost:1101", path="/account/acct")
@Service
public interface AccountService {

    @GetMapping(value = "/showAll")
    public List<Account> showAllAccounts();

    @GetMapping(value = "/findByUsername")
    public Optional<Account> findByUsername(@RequestParam(name="username") String username);

}
