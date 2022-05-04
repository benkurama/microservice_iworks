package com.microservice.workorder.service.repository;

import com.microservice.workorder.service.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 25/04/2022.
 */
@Mapper
@Repository
public interface AccountRepository {

    List<Account> selectAll();

    Account findByUsername(String username);

    List<Map> selectWOAll();
}
