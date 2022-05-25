package com.frontend.ui.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name="installorder-service",url="http://localhost:8090", path="/installorder/inst")
@Service
public interface InstallOrderService {
    @GetMapping(value = "/showAll")
    public List<Map> showall();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();
}
