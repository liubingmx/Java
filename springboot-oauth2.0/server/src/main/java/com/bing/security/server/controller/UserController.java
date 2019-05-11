package com.bing.security.server.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456").toString().equals("$2a$10$jRtwv6hDvOfaY90/c8BFLupr4Yged2tbHuBfnPgSbHYG7akYp/Fa6"));
    }
}


