package com.iworks.reactspring.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long accountId;
    @Column(name = "ACCOUNT_NAME")
    private String username;
    @Column(name = "PASSWORD_")
    private String password;

    private long roleId;
    private String roleName;
    private Set<Role> roles;
}
