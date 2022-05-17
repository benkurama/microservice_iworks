package com.iworks.reactspring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private final String type = "Bearer";
    private Long id;
    private String username;
    private List<String> roles;

}

