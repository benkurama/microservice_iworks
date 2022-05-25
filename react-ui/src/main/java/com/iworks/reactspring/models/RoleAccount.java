package com.iworks.reactspring.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Table(name = "role_account")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ACCOUNT_ID")
    private Long roleAccountId;

    private Account account_id;

    private Set<Role> roles = new HashSet<>();
}
