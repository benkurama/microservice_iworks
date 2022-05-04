package com.frontend.ui.controllers;

import com.frontend.ui.services.AccountService;
import com.frontend.ui.services.OrganizationService;
import com.frontend.ui.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@Controller
public class AccountModController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/showAcc")
    @ResponseBody
    private String mainShow(){

        return "Account Module Controller";
    }

    @RequestMapping("/account/load")
    public String accountLoad(Model model){

        List<Map> resLst = accountService.showAllAccounts();
        List<Map> columnList = accountService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content_accounts :: table_ACC";
    }

    @RequestMapping("/orgz/load")
    public String OrgLoad(Model model){

        List<Map> resLst = organizationService.showAllOrgs();
        List<Map> columnList = organizationService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content_accounts :: table_ORG";
    }

    @RequestMapping("/role/load")
    public String RoleLoad(Model model){

        List<Map> resLst = roleService.showAllRoles();
        List<Map> columnList = roleService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content_accounts :: table_ROL";
    }
}
