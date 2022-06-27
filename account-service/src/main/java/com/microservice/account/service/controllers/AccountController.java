package com.microservice.account.service.controllers;

import com.microservice.account.service.model.MenuClass;
import com.microservice.account.service.repository.AccountRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Default on 21/04/2022.
 */
//@CrossOrigin
@RestController
@RequestMapping("/account/acct")
@Slf4j
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/show")
    public String showString(){
        log.info("ACCOUNT-SERVICE IS IN THE HOUSE");
        return "ACCOUNT-SERVICE IS IN THE HOUSE";
    }

    @GetMapping("/showAll")
    public List<Map> showAllUser(){
        List<Map> accountList = accountRepository.selectAll();

        return accountList;
    }

    @GetMapping("/findByUsername")
    public Map findByUsername(String username){
        Map acc = accountRepository.findByUsername(username);

        return acc;
    }

    @GetMapping("/showTableColumns")
    public List<Map> selectTableColumns() {
    return accountRepository.selectTableColumns();
    }

    @GetMapping("/showMenuResources")
    public List<MenuClass> showMenuResources(String role){


        MenuClass mc = new MenuClass();
        mc.label = "Home";
    //------------------
        List<MenuClass.items> itemsList = new ArrayList<>();
        itemsList.add(new MenuClass.items("Dashboard", "pi pi-fw pi-home", "/"));
    //------------------
        mc.items = itemsList;
    //------------------
        List<MenuClass> mac = new ArrayList<>();
        mac.add(mc);
    //------------------
    //------------------
        mc = new MenuClass();
        mc.label = "JD System Pages";

        itemsList = new ArrayList<>();
        itemsList.add(new MenuClass.items("Graphs", "pi pi-fw pi-chart-bar", "graphs"));
        itemsList.add(new MenuClass.items("Dashboard Responsive", "pi pi-fw pi-chart-bar", "/DashboardResponsive"));

        mc.items = itemsList;

        mac.add(mc);
        //------------------
        return mac;
    }

}
