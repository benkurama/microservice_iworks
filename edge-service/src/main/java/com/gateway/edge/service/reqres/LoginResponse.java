package com.gateway.edge.service.reqres;

import java.util.List;

/**
 * Created by Default on 25/04/2022.
 */
public class LoginResponse {

    private String jwt;

    private String username;

    private List<String> roles;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, String username, List<String> roles) {
        this.jwt = jwt;
        this.username = username;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }

}
