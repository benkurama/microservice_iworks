package com.microservice.workorder.service.repository;

import com.microservice.workorder.service.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Default on 21/04/2022.
 */
@Mapper
@Repository
public interface EmployeeRepository {
    public List< Employee > findAll();

    public int insert(Employee employee);
}
