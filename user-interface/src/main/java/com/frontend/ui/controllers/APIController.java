package com.frontend.ui.controllers;

import com.frontend.ui.model.Account;
import com.frontend.ui.reqres.LoginRequest;
import com.frontend.ui.reqres.LoginResponse;
import com.frontend.ui.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Default on 26/04/2022.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class APIController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/show")
    @ResponseBody
    private String mainShow(){

        return "Main REst Api";
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@Valid @RequestBody LoginRequest loginRequest) {

        //Account res = accountService.findByUsername(loginRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponse( userDetails.getUsername(), roles));
    }

    @GetMapping("/login2")
    public ResponseEntity<LoginResponse> Login2(@RequestParam String username, @RequestParam String password) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new LoginResponse( userDetails.getUsername(), roles));
    }

    @GetMapping("/account/showall")
    public List<Map>  ShowAllAccounts(){

        return accountService.showAllAccounts();
    }

    @GetMapping("/test")
    public String Test(){

        String str = " hello";

        return "";
    }

    @GetMapping("/work-order/load")
    public Map workorderLoad(Model model){

        Map map = new HashMap();
        map.put("message","hi");
        //model.addAttribute("msg","hello");

        return map;
    }

}
