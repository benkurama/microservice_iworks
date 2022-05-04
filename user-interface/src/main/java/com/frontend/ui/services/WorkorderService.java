package com.frontend.ui.services;

import com.frontend.ui.model.Account;

import com.frontend.ui.model.WorkOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 28/04/2022.
 */
@FeignClient(name="workorder-service",url="http://localhost:8090", path="/workorder/work")
@Service
public interface WorkorderService {

    @GetMapping(value = "/showAll")
    public List<Map> showall();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();


}