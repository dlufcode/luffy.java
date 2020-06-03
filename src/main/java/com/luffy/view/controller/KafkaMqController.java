package com.luffy.view.controller;

import com.luffy.view.service.kafkaMq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaMqController {

    @Autowired
    private Producer producer;

    @GetMapping(value = "/send")
    public String send(@RequestParam(value = "message") String message) {
        return producer.send(message);
    }
}
