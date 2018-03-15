package com.arli.moneybook.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_order")
public class Order {
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
