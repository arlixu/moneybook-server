package com.arli.moneybook.controller;

import com.arli.moneybook.dto.ApiResult;
import com.arli.moneybook.dto.request.BuyOrderRequest;
import com.arli.moneybook.dto.response.OrderResponse;
import com.arli.moneybook.entity.Order;
import com.arli.moneybook.repository.OrderRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Mapper beanMapper;

    @RequestMapping("/add")
    @ResponseBody
     public ApiResult addBuyOrder(@Validated @RequestBody BuyOrderRequest buyOrderRequest)
     {
         Order order = beanMapper.map(buyOrderRequest, Order.class);
         order.setSinglePrice(new BigDecimal(order.getTotalPrice()).divide(new BigDecimal(order.getNumber())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
         order.setOrderType("1");
         orderRepository.save(order);
         return  ApiResult.success("/buy/add","succuss");
     }

     @RequestMapping("/list")
     @ResponseBody
    public  ApiResult<List<OrderResponse>> getBuyOrder()
     {
         List<Order> all = orderRepository.findAll();
         List<OrderResponse> responseList=new ArrayList<>();
         beanMapper.map(all,responseList);
         return  ApiResult.success("/buy/list",responseList);
     }
}
