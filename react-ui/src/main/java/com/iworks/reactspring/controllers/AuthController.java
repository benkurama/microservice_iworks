package com.iworks.reactspring.controllers;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.iworks.reactspring.exceptions.AuthenticationException;
import com.iworks.reactspring.models.GraphsModel;
import com.iworks.reactspring.services.AccountService;
import com.iworks.reactspring.services.InstallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.iworks.reactspring.dto.request.LoginRequest;
import com.iworks.reactspring.dto.response.JwtResponse;
import com.iworks.reactspring.security.jwt.JwtUtils;
import com.iworks.reactspring.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    InstallService installService;

    @PostMapping(value = "/sign-in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        }catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @GetMapping("/show")
    public List<Map> showGrap002(){

        List<Map> res = installService.showGraph002();

        //>>>> Get all months by data list and save to dateStr array
            Map<String, Long> mapRegsGroup = res.stream()
                    .collect(Collectors.groupingBy(map -> map.get("subArea").toString(),
                            Collectors.counting() ));

            String selectedArea = "";
            int selectedCount = 0;

            for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
                String k = entry.getKey();
                int v = Integer.parseInt(entry.getValue()+"");

                if(selectedCount < v){
                    selectedCount = v;
                    selectedArea = k;
                }
            }

            final String selArea = selectedArea;
            //--
            List<Map> mapFillFilter = res.stream().filter(map -> map.get("subArea").toString().equals(selArea))
                    .collect(Collectors.toList());
            //--
            int count = mapFillFilter.size();
            String[] dateStr = new String[count];
            int i = 0;

            for (Map entry : mapFillFilter) {
                dateStr[i] = entry.get("dateStr").toString();
                i++;
            }
        //<<<<
        //-------------------------
        List<Map> mapList = new ArrayList<>();

        for (Map.Entry<String, Long> entry : mapRegsGroup.entrySet()) {
            String k = entry.getKey();

            List<Map> mapFill001 = res.stream().filter(map -> map.get("subArea").toString().equals(k))
                    .collect(Collectors.toList());

            int[] data = new int[count];

            for(int ii=0; ii < count; ii++){
                String date = dateStr[ii];
                List<Map> monthData =  mapFill001.stream().filter(map -> map.get("dateStr").toString().equals(date))
                        .collect(Collectors.toList());

                if(monthData.size() != 0){
                    data[ii] = Integer.parseInt( monthData.get(0).get("count").toString() );
                } else {
                    data[ii] = 0;
                }
            }

            Map map = new HashMap();
            //
            map.put(k,data);
            mapList.add(map);
        }

        return mapList;
    }

}
