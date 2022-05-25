package com.frontend.ui.reqres;

import java.util.List;

/**
 * Created by Default on 27/04/2022.
 */
public class LoginResponse {

    private String username;
    private List<String> roles;
    private String jwt;

    public LoginResponse() {
    }

    public LoginResponse(String username, List<String> roles, String tokken) {
        this.username = username;
        this.roles = roles;
        this.jwt = tokken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
