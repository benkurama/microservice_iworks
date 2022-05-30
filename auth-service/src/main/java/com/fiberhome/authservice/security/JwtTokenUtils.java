package com.fiberhome.authservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiberhome.authservice.model.LoginUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
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
public class JwtTokenUtils {

    private String jwtSecret; //= "iworks#2022fh@";

    Algorithm algorithm; //=
    JWTVerifier verifier;// =

    public JwtTokenUtils(String encKey) {
        this.jwtSecret = encKey;
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        this.verifier = JWT.require(algorithm).build();
    }

    public String generateJwtToken(Authentication authentication) {

        LoginUserDetails userPrincipal = (LoginUserDetails) authentication.getPrincipal();

        List<String> authorities = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String jwt = JWT.create()
                .withSubject(String.valueOf(userPrincipal.getId()))
                .withClaim("name", userPrincipal.getUsername())
                .withClaim("authorities",
                        authorities)
                .withIssuedAt(new Date())
                .withIssuer("Fiberhome")
                .withExpiresAt(Date.from(LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.UTC)))
                .sign(algorithm);

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
