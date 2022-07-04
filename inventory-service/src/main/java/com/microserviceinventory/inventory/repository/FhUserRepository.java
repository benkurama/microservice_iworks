package com.microserviceinventory.inventory.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface FhUserRepository {

    List<Map> selectAll();

    Map findByUsernamePassword(String username, String password);

}
