package com.microservice.workorder.service.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface InstallOrderRepository {
    List<Map> selectAll();
    List<Map> selectTableColumns();
}
