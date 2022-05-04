package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.repository.RepairorderRepository;
import com.microservice.workorder.service.repository.WorkorderRepository;
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
@RequestMapping("/workorder/repr")
public class RepairorderController {

    @Autowired
    private RepairorderRepository repairorderRepository;

    @GetMapping("/showAll")
    public List<Map> showall(){

        List<Map> res = repairorderRepository.selectAll();

        return res;
    }

    @GetMapping("/showTableColumns")
    public List<Map> showTableColumns(){
        return repairorderRepository.selectTableColumns();
    }
}
