package com.fiberhome.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiberhome.authservice.model.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    //@Value("${app.jwtSecret}")
    private String jwtSecret = "iworks#2022fh@";

    //private final TokenRedisService tokenRedisService;

    Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();

    public String generateJwtToken(Authentication authentication) {

        LoginUserDetails userPrincipal = (LoginUserDetails) authentication.getPrincipal();

        List<String> authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String jwt = JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withClaim("authorities",
                        authorities)
                .withIssuedAt(new Date())
                .withIssuer("Fiberhome")
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(6).toInstant(ZoneOffset.UTC)))
                .sign(algorithm);


        //tokensEntity = tokenRedisService.save(tokensEntity);
        return jwt;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            DecodedJWT decodedJWT  = verifier.verify(authToken);
            //Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch(Exception e){
            log.error(StringUtils.join(e.getCause()," : ",e.getMessage()));
        }

        return false;
    }
}
