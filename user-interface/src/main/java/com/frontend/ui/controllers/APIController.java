package com.frontend.ui.controllers;

import com.frontend.ui.model.Account;
import com.frontend.ui.reqres.LoginRequest;
import com.frontend.ui.reqres.LoginResponse;
import com.frontend.ui.services.AccountService;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ResponseEntity<?> Login2(@RequestParam(name="username") String username, @RequestParam(name="password") String password) {

        //String username = null, password = null;

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

/*        if(true){
            return ResponseEntity.status(401).body(new LoginResponse( "error occuor", null));
        }*/

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //return ResponseEntity.ok(new LoginResponse( userDetails.getUsername(), roles));
        return ResponseEntity.ok()
                .body(new LoginResponse( userDetails.getUsername(), roles));
    }

    @GetMapping("/account/showall")
    public List<Map>  ShowAllAccounts(){

        return accountService.showAllAccounts();
    }

    @GetMapping("/test")
    public ResponseEntity<?> Test(){

        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

/*        if(auth == null){
            return ResponseEntity.status(401).body(new LoginResponse( "error occuor", null));
        }*/

        String str = " hello";

        return ResponseEntity.ok().body(str);
    }

    @GetMapping("/work-order/load")
    public Map workorderLoad(Model model){

        Map map = new HashMap();
        map.put("message","hi");
        //model.addAttribute("msg","hello");

        return map;
    }

}
