package com.gateway.edge.service.security.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtils {
    @Value("${app.jwtSecret}")
    private String jwtSecret; //= "iworks#2022fh@";
    JWTVerifier verifier;
    Algorithm algorithm;

    @PostConstruct
    public void init(){
        this.algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        this.verifier = JWT.require(algorithm).build();
    }

    private boolean isTokenExpired(String token) {
        return this.getExpiryToken(token).before(new Date());
    }

    public Map<String, Claim> getAllClaimsFromToken(String token) {
        DecodedJWT jwt = verifier.verify(token);

        return jwt.getClaims();
    }

    public Date getExpiryToken(String token) {
        //return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        DecodedJWT jwt = verifier.verify(token);

        return jwt.getExpiresAt();
    }

    public boolean isInvalid(String token) {
        return this.isTokenExpired(token);
    }

}
