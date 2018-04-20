package com.arli.moneybook.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "t_goods")
public class Goods {
    @Id
    @GeneratedValue
    private Integer goodsId;
    private String goodsName;
    private  Integer goodsCount=0;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt=new Date();
    private Date updateAt=new Date();

    public Goods() {
    }

    public Goods(String goodsName) {
        this.goodsName = goodsName;
    }
}
