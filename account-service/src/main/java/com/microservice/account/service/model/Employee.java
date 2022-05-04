package com.microservice.account.service.model;


import lombok.Getter;
import lombok.Setter;

/**
 * Created by Default on 21/04/2022.
 */

@Getter
@Setter
public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
