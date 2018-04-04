package com.arli.moneybook.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SellOrderRequest {
    @NotNull(message="商品不存在。")
    private String goodsName;
    @NotNull(message="商品数量不能为空。")
    private Integer number;
    @NotNull(message="商品总价不能为空。")
    private Double totalPrice;
}
