package com.isen.m2.handfullproject.restapi.Rest.API.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "redirect:/user/login";
    }

    @GetMapping("/restricted")
    public String restricted() {
        return "restricted";
    }
}