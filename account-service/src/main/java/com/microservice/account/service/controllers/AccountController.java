package com.microservice.account.service.controllers;

import com.microservice.account.service.model.Account;
import com.microservice.account.service.model.Employee;
import com.microservice.account.service.repository.AccountRepository;
import com.microservice.account.service.repository.EmployeeRepository;
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
@RequestMapping("/account/acct")
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

    @GetMapping("/showAll")
    public List<Map> showAllUser(){
        List<Map> accountList = accountRepository.selectAll();

        return accountList;
    }

    @GetMapping("/findByUsername")
    public Map findByUsername(String username){
        Map acc = accountRepository.findByUsername(username);

        return acc;
    }

    @GetMapping("/showTableColumns")
    public List<Map> selectTableColumns() {
    return accountRepository.selectTableColumns();
    }

}
