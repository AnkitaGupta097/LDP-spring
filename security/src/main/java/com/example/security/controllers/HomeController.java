package com.example.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {

        return "hello";
    }


    @GetMapping("/managers")
    public String showManagers() {

        return "manager";
    }


    @GetMapping("/admins")
    public String showAdmin() {

        return "admin";
    }
    @GetMapping("/access-denied")
    public String accessDenied() {

        return "access-denied";
    }
    @GetMapping("/abc")
    public String abc() {

        return "welcome";
    }


}
