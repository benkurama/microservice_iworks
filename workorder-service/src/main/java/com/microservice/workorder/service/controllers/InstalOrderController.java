package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.repository.InstallOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
