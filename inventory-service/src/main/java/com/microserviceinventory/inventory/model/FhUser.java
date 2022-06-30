package com.microserviceinventory.inventory.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Default on 30/06/2022
 */

@Getter
@Setter
public class FhUser {

    private long id;
    private String fhid;
    private String proj_name;
    private String display_name;
    private String username;
    private String password;
    private String department;
    private String position;
    private String status;
}
