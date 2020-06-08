package com.luffy.view.service.dataCanal;

import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.*;
import com.github.shyiko.mysql.binlog.event.deserialization.EventDeserializer;
import com.google.gson.Gson;
import com.luffy.view.enums.BinLogEventEnum;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BinLogService {

    public void listen(Database database, List<DataTable> dataTables) throws IOException {
        BinaryLogClient client = new BinaryLogClient(
                database.getIp(),
                database.getPort(),
                database.getUser(),
                database.getPw()
        );
        //
        EventDeserializer eventDeserializer = new EventDeserializer();
        eventDeserializer.setCompatibilityMode(
                EventDeserializer.CompatibilityMode.DATE_AND_TIME_AS_LONG,
                EventDeserializer.CompatibilityMode.CHAR_AND_BINARY_AS_BYTE_ARRAY
        );
        //
        Gson gson = new Gson();
        client.setEventDeserializer(eventDeserializer);
        Map<Long, String> id2Tbl = new HashMap<>();
        Map<Integer, String> records = new HashMap<>();
        client.registerEventListener(new BinaryLogClient.EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println("Event:" + gson.toJson(event));
                //
                EventData eventData = event.getData();
                if (eventData != null) {
                    dealWithData(eventData, id2Tbl, dataTables, records);
                    if (!CollectionUtils.isEmpty(records)) {
                        System.out.println(String.format("%s:%s", "[records]", gson.toJson(records)));
                    }
                }
            }
        });
        System.out.println("监听启动！");
        client.connect();
    }

    private void dealWithData(EventData eventData, Map<Long, String> id2Tbl, List<DataTable> dataTables, Map<Integer, String> records) {
        Gson gson = new Gson();
        List<String> tbls = new ArrayList<>();
        for (DataTable table : dataTables) {
            tbls.add(String.format("%s.%s", table.getDatabase(), table.getTbl()));
        }
        if (eventData instanceof TableMapEventData) {
            TableMapEventData data = (TableMapEventData) eventData;
            id2Tbl.put(data.getTableId(), String.format("%s.%s", data.getDatabase().toString(), data.getTable().toString()));
            System.out.println("id2Tbl:" + gson.toJson(id2Tbl));
        }
        //
        if (eventData instanceof UpdateRowsEventData) {
            UpdateRowsEventData data = (UpdateRowsEventData) eventData;
            if (!filterTable(id2Tbl.get(data.getTableId()), tbls)) {
                return;
            }
            for (Map.Entry<Serializable[], Serializable[]> entry : data.getRows()) {
                records.put(BinLogEventEnum.UPDATE_EVENT.getEventId(), gson.toJson(entry.getValue()));
            }
        }
        //
        if (eventData instanceof WriteRowsEventData) {
            WriteRowsEventData data = (WriteRowsEventData) eventData;
            if (!filterTable(id2Tbl.get(data.getTableId()), tbls)) {
                return;
            }
            for (Serializable[] row : data.getRows()) {
                records.put(BinLogEventEnum.INSERT_EVENT.getEventId(), gson.toJson(row));
            }
        }
        //
        if (eventData instanceof DeleteRowsEventData) {
            DeleteRowsEventData data = (DeleteRowsEventData) eventData;
            if (!filterTable(id2Tbl.get(data.getTableId()), tbls)) {
                return;
            }
            for (Serializable[] row : data.getRows()) {
                records.put(BinLogEventEnum.DELETE_EVENT.getEventId(), gson.toJson(row));
            }

        }
    }

    private boolean filterTable(String tbl, List<String> tbls) {
        Gson gson = new Gson();
        System.out.println(String.format("filter table ,tbls:%s,tbl:%s", gson.toJson(tbls), tbl));
        return tbls.contains(tbl);
    }


}
