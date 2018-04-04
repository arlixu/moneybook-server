package com.arli.moneybook.controller;

import com.arli.moneybook.dto.ApiResult;
import com.arli.moneybook.dto.request.SellOrderRequest;
import com.arli.moneybook.entity.BuyOrder;
import com.arli.moneybook.entity.Goods;
import com.arli.moneybook.entity.SellOrder;
import com.arli.moneybook.repository.BuyOrderRepository;
import com.arli.moneybook.repository.GoodsRepository;
import com.arli.moneybook.repository.SellOrderRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/sell")
public class SellController {

    @Autowired
    private SellOrderRepository orderRepository;
    @Autowired
    private Mapper beanMapper;
    @Autowired
    private GoodsRepository goodsRepository;

    @RequestMapping("/save")
    @ResponseBody
     public ApiResult saveSellOrder(@Validated @RequestBody SellOrderRequest sellOrderRequest)
     {
         SellOrder sellOrder = beanMapper.map(sellOrderRequest, SellOrder.class);
         sellOrder.setSinglePrice(new BigDecimal(sellOrder.getTotalPrice()).divide(new BigDecimal(sellOrder.getNumber())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
         sellOrder.setOrderType("2");
         Goods goods = goodsRepository.findOneByGoodsName(sellOrder.getGoodsName());
         if(goods.getGoodsCount()-sellOrder.getNumber()<0)
         {
             return  ApiResult.fail("/sell/save","没有对应数量的"+goods.getGoodsName());
         }
         goods.setGoodsCount(goods.getGoodsCount()-sellOrder.getNumber());
         goodsRepository.save(goods);
         orderRepository.save(sellOrder);
         return  ApiResult.success("/sell/save","succuss");
     }

     @RequestMapping("/list")
     @ResponseBody
    public  ApiResult<Page<SellOrder>> getSellOrder(@RequestParam Integer pageNo,@RequestParam Integer pageSize)
     {
         Page<SellOrder> all = orderRepository.findAll(new PageRequest(pageNo,pageSize, Sort.Direction.DESC,"createAt"));
         return  ApiResult.success("/sell/list",all);
     }

    @RequestMapping("/remove")
    @ResponseBody
    public  ApiResult<String> removeSellOrder(@RequestParam Integer orderId)
    {
        SellOrder sellOrder = orderRepository.findOne(orderId);
        Goods goods = goodsRepository.findOneByGoodsName(sellOrder.getGoodsName());
        goods.setGoodsCount(goods.getGoodsCount()+sellOrder.getNumber());
        goodsRepository.save(goods);
        orderRepository.delete(orderId);
        return  ApiResult.success("/sell/remove","删除成功");
    }
}
