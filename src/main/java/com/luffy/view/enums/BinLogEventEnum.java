package com.luffy.view.enums;

import lombok.Getter;

@Getter
public enum BinLogEventEnum {
    TABLE_MAP_EVENT(1, "表映射事件"),

    UPDATE_EVENT(2, "更新事件"),

    DELETE_EVENT(3, "删除事件"),

    INSERT_EVENT(4, "新增事件");

    private Integer eventId;
    private String desc;

    BinLogEventEnum(Integer id, String desc) {
        this.eventId = id;
        this.desc = desc;
    }

}
