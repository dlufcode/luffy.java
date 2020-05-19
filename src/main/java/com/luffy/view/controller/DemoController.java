package com.luffy.view.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ViewTest;
import service.impl.ViewTestImpl;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping(value = "/run")
    public String run(){
        ViewTest viewTest = new ViewTestImpl();
        return viewTest.test();
    }
}
