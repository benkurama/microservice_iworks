package com.microservice.workorder.service.controllers;

import com.microservice.workorder.service.model.Account;
import com.microservice.workorder.service.model.Employee;
import com.microservice.workorder.service.repository.AccountRepository;
import com.microservice.workorder.service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Default on 21/04/2022.
 */
@RestController
//@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/show")
    public String showString(){
        return "ACCOUNT-SERVICE IS IN THE HOUSE";
    }

    @GetMapping("/showemp")
    public List<Employee> showEmployee(){

        List<Employee> empList =  employeeRepository.findAll();


        return empList;
    }

    @GetMapping("/showAllAccounts")
    public List<Account> showAllUser(){
        List<Account> accountList = accountRepository.selectAll();

        return accountList;
    }

    @GetMapping("/findByUsername")
    public Account findByUsername(String username){
        Account acc = accountRepository.findByUsername(username);

        return acc;
    }

    @GetMapping("/showAllWO")
    public List<Map> showAllUserWO(){

        return accountRepository.selectWOAll();
    }

}
