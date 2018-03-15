package com.arli.moneybook.dto.response;

import lombok.Data;

import java.util.Date;
@Data
public class OrderResponse {
    private Integer orderId;
    private String goodsName;
    private Integer number;
    private Double totalPrice;
    private Double singlePrice;
    private String orderType;
    private Date  createAt=new Date();
    private Date updateAt=new Date();
}
