package com.iworks.reactspring.repository;

import com.iworks.reactspring.models.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface AccountMapper {
    Optional<Account> findByUsername(@Param("userName") String userName);
}
