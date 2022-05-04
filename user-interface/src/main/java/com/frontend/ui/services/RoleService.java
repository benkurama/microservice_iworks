package com.frontend.ui.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@FeignClient(name = "role-service", url = "http://localhost:8090", path = "/account/role")
@Service
public interface RoleService {
    @GetMapping(value = "/showAll")
    public List<Map> showAllRoles();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();
}
