package com.frontend.ui.controllers;

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

    @RequestMapping("/showAcc")
    @ResponseBody
    private String mainShow(){

        return "Account Module Controller";
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
