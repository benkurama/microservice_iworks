package com.fiberhome.authservice.service;

import com.fiberhome.authservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserService {
   Optional<Account> getByUsername(String username);
}
