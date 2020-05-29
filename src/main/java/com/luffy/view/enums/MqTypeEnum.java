package com.luffy.view.enums;

import lombok.Getter;

@Getter
public enum MqTypeEnum {
    RABBIT_DEFAULT(0, "default.exchange"),
    RABBIT_DIRECT(1, "direct.exchange"),
    RABBIT_FANOUT(2, "fanout.exchange"),
    RABBIT_TOPIC(3, "topic.exchange");
    private Integer type;
    private String desc;

    MqTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

}
