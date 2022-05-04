package com.frontend.ui.controllers;

import com.frontend.ui.services.RepairorderService;
import com.frontend.ui.services.WorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 04/05/2022.
 */
@Controller
public class WorkorderdModController {

    @Autowired
    private WorkorderService workorderService;

    @Autowired
    private RepairorderService repairorderService;

    @RequestMapping("/work-order/load")
    public String workorderLoad(Model model){

        List<Map> resLst = workorderService.showall();
        List<Map> columnList = workorderService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content :: table_WO";
    }

    @RequestMapping("/repair-order/load")
    public String repairorderLoad(Model model){

        List<Map> resLst = repairorderService.showall();
        List<Map> columnList = repairorderService.showTableColumns();

        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content :: table_REPR";
    }
}
