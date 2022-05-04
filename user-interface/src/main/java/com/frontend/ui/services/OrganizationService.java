package com.frontend.ui.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@FeignClient(name = "orgz-service", url = "http://localhost:8090", path = "/account/orgz")
@Service
public interface OrganizationService {
    @GetMapping(value = "/showAll")
    public List<Map> showAllOrgs();

    @GetMapping(value = "/showTableColumns")
    public List<Map> showTableColumns();
}
