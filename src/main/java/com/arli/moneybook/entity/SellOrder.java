package com.arli.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date  createAt=new Date();
    private Date updateAt=new Date();
}
