package com.bing.security.client.controller;

import com.bing.security.common.response.ResponseData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public ResponseData user(Principal principal) {
        return ResponseData.success(principal);
    }
}
