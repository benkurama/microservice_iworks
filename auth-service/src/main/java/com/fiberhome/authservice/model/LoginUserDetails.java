package com.fiberhome.authservice.model;

import java.util.*;

import com.fiberhome.authservice.service.LoginUserDetailsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@RequiredArgsConstructor
public class LoginUserDetails implements UserDetails {
    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(Long id, String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static LoginUserDetails build(Account acct) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        acct.getRoles().forEach(
                role -> authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoleName().toUpperCase())))
        ;

        return new LoginUserDetails(
                acct.getAccountId(),
                acct.getUsername(),
                acct.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LoginUserDetails user = (LoginUserDetails) o;
        return Objects.equals(id, user.id);
    }
}
