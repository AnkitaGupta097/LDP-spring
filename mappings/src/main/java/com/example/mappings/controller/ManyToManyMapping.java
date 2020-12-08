package com.example.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mappings.Service.ManyToManyService;

@RestController
@RequestMapping("/manyToMany")
public class ManyToManyMapping {

    @Autowired
    private ManyToManyService service;

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
