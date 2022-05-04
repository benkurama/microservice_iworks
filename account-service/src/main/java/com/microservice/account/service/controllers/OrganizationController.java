package com.microservice.account.service.controllers;

import com.microservice.account.service.repository.OrganizationRepository;
import com.microservice.account.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@RestController
@RequestMapping("/account/orgz")
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @GetMapping("/showAll")
    public List<Map> showAll(){
        List<Map> accountList = organizationRepository.selectAll();

        return accountList;
    }

    @GetMapping("/showTableColumns")
    public List<Map> selectTableColumns() {
        return organizationRepository.selectTableColumns();
    }
}
