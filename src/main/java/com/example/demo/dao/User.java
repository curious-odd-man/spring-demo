package com.example.demo.dao;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {
    @Id
    private String  aUserName;
    private String  aPassword;
    private String  aRoles;     // FIXME: Another table for user roles
    private boolean aIsActive;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(aRoles.split("|"))
                     .map(SimpleGrantedAuthority::new)
                     .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return aPassword;
    }

    @Override
    public String getUsername() {
        return aUserName;
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
        return aIsActive;
    }

    public String getUserName() {
        return aUserName;
    }

    public void setUserName(String userName) {
        aUserName = userName;
    }

    public void setPassword(String password) {
        aPassword = password;
    }

    public String getRoles() {
        return aRoles;
    }

    public void setRoles(String roles) {
        aRoles = roles;
    }

    public boolean isActive() {
        return aIsActive;
    }

    public void setActive(boolean active) {
        aIsActive = active;
    }
}
