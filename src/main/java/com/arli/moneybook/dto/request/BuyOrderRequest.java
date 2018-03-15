package com.arli.moneybook.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BuyOrderRequest {
    @NotNull(message="商品名不能为空。")
    private String goodsName;
    @NotNull(message="商品数量不能为空。")
    private Integer number;
    @NotNull(message="商品总价不能为空。")
    private Double totalPrice;
}
