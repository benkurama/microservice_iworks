package com.microservice.account.service.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Default on 25/04/2022.
 */
@Getter
@Setter
public class Account {
    private long id_;
    private String name_;
    private String username_;
    private String password_;
    private String email_;
}
