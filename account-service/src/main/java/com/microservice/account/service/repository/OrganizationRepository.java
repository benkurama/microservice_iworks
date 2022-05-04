package com.microservice.account.service.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@Mapper
@Repository
public interface OrganizationRepository {
    List<Map> selectAll();
    List<Map> selectTableColumns();
}
