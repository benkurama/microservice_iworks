package com.fiberhome.authservice.repository;

import com.fiberhome.authservice.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface LoginUserDetailsMapper {
    Optional<Account> findByUsername(@Param("userName") String userName);
}
