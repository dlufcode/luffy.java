package com.luffy.view.controller;

import com.luffy.view.service.dataCanal.CanalListen;
import com.luffy.view.service.dataCanal.DataTable;
import com.luffy.view.service.dataCanal.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping(value = "/canal")
public class CanalController {
    @Autowired
    private CanalListen canalListen;

    @GetMapping(value = "/listen")
    public String listen() {
        Database database = new Database();
        database.setIp("127.0.0.1");
        database.setPort(3306);
        database.setUser("root");
        database.setPw("");
        //
        DataTable table = new DataTable();
        table.setDatabase("test");
        table.setTbl("test_delivery_order");
        canalListen.setDatabase(database);
        canalListen.setDataTables(Collections.singletonList(table));
        canalListen.listen();
        return "done";
    }
}
