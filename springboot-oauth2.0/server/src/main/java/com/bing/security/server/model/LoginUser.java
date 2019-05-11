package com.bing.security.server.model;

import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;

public class LoginUser extends org.springframework.security.core.userdetails.User {

    private String username;
    private String password;


    public LoginUser(String username, String password) {
        super(username, password,  AuthorityUtils.commaSeparatedStringToAuthorityList("/user"));
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
