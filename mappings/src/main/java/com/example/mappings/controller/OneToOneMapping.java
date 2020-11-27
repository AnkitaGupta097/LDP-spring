package com.example.mappings.controller;

import com.example.mappings.Service.OneToOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oneToOne")
public class OneToOneMapping {

    @Autowired
    private OneToOneService service;

    @GetMapping("/create")
    public void create(){
        service.create();
    }

    @GetMapping("/get")
    public void get(){
        service.get();
    }

    @GetMapping("/delete")
    public void delete(){
        service.delete();
    }

}
