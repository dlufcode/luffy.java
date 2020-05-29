package com.luffy.view.controller;

import com.luffy.view.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMqController {

    @Autowired
    private MqService mqService;

    @GetMapping(value = "/product")
    public String product(@RequestParam(value = "exchange") Integer type,
                          @RequestParam(value = "routkey") String routKey,
                          @RequestParam(value = "message") String message) {
        mqService.product(type, routKey, message);
        return "done";
    }

    @GetMapping(value = "/delayMessage")
    public String delayMessage(@RequestParam(value = "message") String message) {
        mqService.delayProduct(String.format("[%s]: {%s}", new Date().toString(), message));
        return "done";
    }
}
