package com.frontend.ui.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.frontend.ui.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by Default on 26/04/2022.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map user = accountService.findByUsername(username);

        String usernamae = user.get("ACCOUNT_NAME").toString();
        String password = user.get("PASSWORD_").toString();

        if (user != null) {
            return new User(usernamae,password , buildSimpleGrantedAuthorities("ROLE_USER"));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        /*for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }*/

        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
