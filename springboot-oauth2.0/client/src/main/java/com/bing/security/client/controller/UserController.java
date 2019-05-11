package com.bing.security.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class UserController {

    @RequestMapping({ "/user", "/me" })
    public Map<String, Object> user(Principal principal, HttpServletRequest request) {
        System.out.println(request.getRemoteHost());
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        map.put("user", principal);
        return map;
    }
}
