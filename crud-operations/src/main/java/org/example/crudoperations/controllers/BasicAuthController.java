package org.example.crudoperations.controllers;

import org.example.crudoperations.entity.User;
import org.example.crudoperations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

//Controller
@CrossOrigin(origins="${client.url}")
@RestController
public class BasicAuthController {

    @Autowired
    UserService userService;

    @GetMapping( "/basicauth")
    public String checkLogin() {
        return "You are authenticated";
    }
    @GetMapping("/user")
    public org.springframework.security.core.userdetails.User currentUser(Authentication authentication) {
        return (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
    }
    @PostMapping("/signup")
    public User registerUserAccount(@RequestBody User signedUpUser) {

        return  userService.save(signedUpUser);

    }

}