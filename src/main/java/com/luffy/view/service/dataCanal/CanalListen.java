package com.luffy.view.service.dataCanal;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CanalListen {
    private Database database;
    private List<DataTable> dataTables;

    @Autowired
    private BinLogService binLogService;

    public void listen() {
        try {
            binLogService.listen(database, dataTables);
        } catch (Exception e) {
            System.out.println("listen exception:" + e.getMessage());
        }

    }
}
