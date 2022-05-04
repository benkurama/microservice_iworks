package com.microservice.workorder.service.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 02/05/2022.
 */
@Mapper
@Repository
public interface WorkorderRepository {
    List<Map> selectAll();

    List<Map> selectTableColumns();
}
