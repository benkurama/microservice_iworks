package com.iworks.reactspring.security.services;

import com.iworks.reactspring.models.Account;
import com.iworks.reactspring.repository.AccountMapper;
import com.iworks.reactspring.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
//@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    //private final AccountMapper accountMapper;
    @Autowired
    public AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map user = accountService.findByUsername(username);
        Account acct = new Account();

        //acct.setAccountId("");
        acct.setUsername(user.get("ACCOUNT_NAME").toString());
        acct.setPassword(user.get("PASSWORD_").toString());
        acct.setRoleName("ROLE_USER");
        /*Account acct = accountMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));*/

        return UserDetailsImpl.build(acct);
    }
}
