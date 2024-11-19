package com.example.kanban.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public class JwtAuthentication {
    private String username;
    private List<SimpleGrantedAuthority> authorities;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
