package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.repository.InstallOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/installorder/inst")
@Slf4j
public class InstalOrderController {

    @Autowired
    private InstallOrderRepository installOrderRepository;

    @GetMapping("/showAll")
    public List<Map> showall(){

        List<Map> res = installOrderRepository.selectAll();

        return res;
    }

    @GetMapping("/showTableColumns")
    public List<Map> showTableColumns(){
        return installOrderRepository.selectTableColumns();
    }

    @GetMapping("/showGraph001")
    public List<Map> showGraph001(){
        return installOrderRepository.selectGraph001();
    }

    @GetMapping("/showGraph002")
    public List<Map> showGraph002(){
        return installOrderRepository.selectGraph002();
    }

    @GetMapping("/showReceiptGraph001")
    public List<Map> showReceiptGraph001(){
        return installOrderRepository.selectReceiptGraph001();
    }

    @GetMapping("/showReceiptGraph002")
    public List<Map> showReceiptGraph002(){
        return installOrderRepository.selectReceiptGraph002();
    }

    @GetMapping("/selectTransHistoryGraph001")
    public List<Map> selectTransHistoryGraph001(){
        return installOrderRepository.selectTransHistoryGraph001();
    }

    @GetMapping("/selectDashbordCount")
    public Map selectDashbordCount(){
        List<Map> userCount = installOrderRepository.selectUserCount();
        List<Map> installCount = installOrderRepository.selectInstallCount();
        List<Map> transCount = installOrderRepository.selectTransCount();
        List<Map> shipCount = installOrderRepository.selectShipCount();

        Map map = new HashMap();

        map.put("UserCount", userCount);
        map.put("InstallCount", installCount);
        map.put("TransCount", transCount);
        map.put("ShipCount", shipCount);

        return map;
    }

    @GetMapping("/selectCurrentDataInstallOrder")
    public List<Map> selectCurrentDataInstallOrder(){

        return installOrderRepository.selectCurrentDataInstallOrder();
    }

    @GetMapping("/selecAreaGroupByState")
    public List<Map> selecAreaGroupByState(){

        return installOrderRepository.selecAreaGroupByState();
    }


}
