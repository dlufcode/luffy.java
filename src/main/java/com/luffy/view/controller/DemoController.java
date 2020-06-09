package com.luffy.view.controller;

import com.google.gson.Gson;
import com.luffy.view.domain.Order;
import com.luffy.view.domain.TestDeliveryOrder;
import com.luffy.view.query.OrderAddQuery;
import com.luffy.view.service.db.DbService;
import com.luffy.view.service.dbTest.TestDeliveryOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ViewTest;
import service.impl.ViewTestImpl;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private TestDeliveryOrderService testDeliveryOrderService;

    @Autowired
    private DbService dbService;

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

    @GetMapping(value = "/orderList")
    public String getOrderList() {
        Gson gson = new Gson();
        return gson.toJson(dbService.getOrderList());
    }

    @PostMapping(value = "/add/order")
    public String addOrder(@RequestBody OrderAddQuery order) {
        Order orderInfo = new Order();
        orderInfo.setOrderId(order.getOrderId());
        orderInfo.setSkuId(order.getSkuId());
        orderInfo.setStatus(order.getStatus());
        Date date = new Date();
        orderInfo.setCtime(date);
        orderInfo.setMtime(date);
        dbService.addOrder(orderInfo);
        return "success!";
    }
}
