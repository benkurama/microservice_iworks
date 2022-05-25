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

    List<Map> selectGraph001();

    List<Map> selectGraph002();

    List<Map> selectReceiptGraph001();

    List<Map> selectReceiptGraph002();

    List<Map> selectTransHistoryGraph001();
}
