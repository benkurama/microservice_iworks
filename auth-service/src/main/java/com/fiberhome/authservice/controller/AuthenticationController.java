package com.fiberhome.authservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiberhome.authservice.model.Account;
import com.fiberhome.authservice.model.Role;
import com.fiberhome.authservice.service.LoginUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth2")
@CrossOrigin
public class AuthenticationController {

    /*  @GetMapping(value = "/validateConnect", produces = {MediaType.APPLICATION_JSON_VALUE})
      public ResponseEntity<ConnValidationResponse> validateGet(HttpServletRequest request) {

          String username = (String) request.getParameter("username");
          List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) request.getAttribute("authorities");
          return ResponseEntity.ok(ConnValidationResponse.builder().status("OK").methodType(HttpMethod.GET.name())
                  .username(username).authorities(grantedAuthorities)
                  .isAuthenticated(true).build());
      }*/
    @Value("${app.jwtSecret}")
    private String jwtSecret;
    private final LoginUserDetailsService loginUserDetailsService;

    public AuthenticationController(LoginUserDetailsService loginUserDetailsService) {
        this.loginUserDetailsService = loginUserDetailsService;
    }

    @GetMapping("/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = StringUtils.substring(authorizationHeader, "Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);

                String username = decodedJWT.getSubject();
                Account acct = loginUserDetailsService.getAccount(username);
                String accessToken = JWT.create()
                        .withSubject(acct.getUsername())
                        .withClaim("authorities",
                                acct.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList())
                        )
                        .withIssuer(request.getRequestURI())
                        .withExpiresAt(Date.from(LocalDateTime.now().plusMinutes(30).toInstant(ZoneOffset.UTC)))
                        .sign(Algorithm.HMAC256(jwtSecret.getBytes()));
             /* String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
              Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
              stream(roles).forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));

              UsernamePasswordAuthenticationToken authenticationToken =
                      new UsernamePasswordAuthenticationToken(username,null, authorities);
              SecurityContextHolder.getContext().setAuthentication(authenticationToken); */
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                response.setHeader("Error", e.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
    }
}
