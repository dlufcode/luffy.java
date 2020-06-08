package com.luffy.view.config.datasource;

/***
 * 枚举区分读写库
 * @author ljy
 *
 */
public enum DataSourceType {
    read("SLAVE", "从库"),
    write("MASTER", "主库");

    private String type;
    private String name;

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }


}
