package com.microserviceinventory.inventory.controllers;

import com.microserviceinventory.inventory.repository.FhUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventory/invt")
@Slf4j
public class FhUserController {

    @Autowired
    private FhUserRepository fhUserRepository;

    @GetMapping("/show")
    public String showString(){
        log.info("INVENTORY-SERVICE IS IN THE HOUSE");
        return "INVENTORY-SERVICE IS IN THE HOUSE";
    }

    @GetMapping("/showAll")
    public List<Map> showAll(){
        List<Map> userList = fhUserRepository.selectAll();
        return  userList;
    }

}
