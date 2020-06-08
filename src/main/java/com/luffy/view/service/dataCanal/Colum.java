package com.luffy.view.service.dataCanal;

import lombok.Data;

@Data
public class Colum {
    /**
     * 列名
     */
    public final String colName;
    /**
     * 类型
     */
    public final String dataType;

    public int inx;

    public Colum(String schema, String table, int idx, String colName, String dataType) {
        this.colName = colName;
        this.dataType = dataType;
        this.inx = idx;
    }
}
