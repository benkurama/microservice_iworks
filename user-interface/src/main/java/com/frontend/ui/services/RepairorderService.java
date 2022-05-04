package com.frontend.ui.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@FeignClient(name="repair-service",url="http://localhost:8090", path="/workorder/repr")
@Service
public interface RepairorderService {
    @GetMapping(value = "/showAll")
    public List<Map> showall();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();
}
