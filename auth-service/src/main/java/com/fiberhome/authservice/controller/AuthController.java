package com.fiberhome.authservice.controller;

import com.fiberhome.authservice.dto.requests.LoginRequest;
import com.fiberhome.authservice.dto.response.JwtResponse;
import com.fiberhome.authservice.exceptions.AuthenticationException;
import com.fiberhome.authservice.model.LoginUserDetails;
import com.fiberhome.authservice.model.TokensEntity;
import com.fiberhome.authservice.security.JwtTokenUtils;
import com.fiberhome.authservice.security.filters.JWTAuthenticationFilter;
import jdk.net.SocketFlow;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws AuthenticationException {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                TokensEntity jwt = jwtTokenUtils.generateJwtToken(authentication);

                return ResponseEntity.ok(JwtResponse.builder().id(jwt.getId())
                        .username(jwt.getUsername())
                        .roles(jwt.getRole())
                        .isAuthenticated(true).build());
            }
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS", e);
        }


        /*
        String jwt = jwtUtils.generateJwtToken(authentication);

        LoginUserDetails userDetails = (LoginUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse("check",
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                true));*/
        return null;
    }
}