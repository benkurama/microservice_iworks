package com.microservice.account.service.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 02/05/2022.
 */
@Mapper
@Repository
public interface RoleRepository {
    List<Map> selectAll();
    List<Map> selectTableColumns();
}
