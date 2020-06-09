package com.luffy.view.query;

import lombok.Data;

import java.util.Date;

@Data
public class OrderAddQuery {
    private Integer orderId;
    private Integer skuId;
    private Integer status;
    private Date ctime;
    private Date mtime;
}
