package com.luffy.view.controller;

import com.google.gson.Gson;
import com.luffy.view.domain.TestDeliveryOrder;
import com.luffy.view.service.dbTest.TestDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ViewTest;
import service.impl.ViewTestImpl;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private TestDeliveryOrderService testDeliveryOrderService;

    @GetMapping(value = "/run")
    public String run() {
        ViewTest viewTest = new ViewTestImpl();
        return viewTest.test();
    }

    @GetMapping(value = "/list")
    public String getList() {
        List<TestDeliveryOrder> orderList = testDeliveryOrderService.getList();
        Gson gson = new Gson();
        return gson.toJson(orderList);
    }
}
