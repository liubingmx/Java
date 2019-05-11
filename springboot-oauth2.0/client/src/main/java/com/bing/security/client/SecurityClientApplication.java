package com.bing.security.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@SpringBootApplication
@EnableOAuth2Client
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication.class, args);
    }





}
