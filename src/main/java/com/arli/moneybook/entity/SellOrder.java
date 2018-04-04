package com.arli.moneybook.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "t_sell_order")
public class SellOrder {
    @Id
    @GeneratedValue
    private Integer orderId;
    private String goodsName;
    private Integer number;
    private Double totalPrice;
    private Double singlePrice;
    private String orderType;
    private Date  createAt=new Date();
    private Date updateAt=new Date();
}
