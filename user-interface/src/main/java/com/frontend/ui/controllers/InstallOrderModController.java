package com.frontend.ui.controllers;

import com.frontend.ui.services.InstallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class InstallOrderModController {
    @Autowired
    InstallOrderService installOrderService;

    @RequestMapping("/install-order/load")
    public String installOrderLoad(Model model){

        List<Map> resLst = installOrderService.showall();
        List<Map> columnList = installOrderService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content_installorder :: table_IO";
    }
}
