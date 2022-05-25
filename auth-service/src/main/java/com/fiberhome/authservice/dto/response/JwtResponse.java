package com.fiberhome.authservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class JwtResponse {
    private final String type = "Bearer ";
    private String id;
    private String username;
    private List<String> roles;
    private boolean isAuthenticated;

}

