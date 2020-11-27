package com.example.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.mappings.Service.OneToManyService;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/oneToMany")
@RestController
public class OneToManyMapping {

    @Autowired
    private OneToManyService service;

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
