package com.frontend.ui.controllers;

import com.frontend.ui.model.Account;
import com.frontend.ui.model.WorkOrder;
import com.frontend.ui.services.AccountService;
import com.frontend.ui.services.WorkorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 26/04/2022.
 */

@Controller
public class MainController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private WorkorderService workorderService;

    @RequestMapping("/show")
    @ResponseBody
    private String mainShow(){

        return "Main REst Api";
    }

    @RequestMapping("/")
    public String home(Model model) {

        //List<Account> accLst = accountService.showAllAccounts();

        //model.addAttribute("accLst", accLst);
        //System.out.println("Home Logger");

        return "/home";
    }

    @RequestMapping("/hello")
    public String hello() {
        //model.put("message", this.message);
        return "/hello";
    }

    @RequestMapping("/service-order/load")
    public String serviceOrder(Model model) {
        //model.put("message", this.message);
        //List<WorkOrder> resLst = workorderService.showall();
        List<Map> resLst = workorderService.showall();


        model.addAttribute("resLst", resLst);


        //model.addAttribute("msg","hello");
        return "/home";
    }

    @RequestMapping("/work-order/null")
    public String workorderNull(Model model){

        return "/fragments/content_home :: table";
    }

    @RequestMapping("/work-order/load")
    public String workorderLoad(Model model){

        /*List<WorkOrder> resLst = workorderService.showall();*/
        List<Map> resLst = workorderService.showall();
        List<Map> columnList = workorderService.showTableColumns();


        model.addAttribute("resLst", resLst);
        model.addAttribute("cols", columnList);

        return "/fragments/content :: table_WO";
    }



/*    @RequestMapping("/test")
    public void test(Model model) {
        //model.put("message", this.message);
        List<Account> accLst = accountService.showAllAccounts();

        model.addAttribute("accLst", accLst);

    }*/

}
