package com.arli.moneybook.controller;

import com.arli.moneybook.dto.ApiResult;
import com.arli.moneybook.dto.request.BuyOrderRequest;
import com.arli.moneybook.entity.BuyOrder;
import com.arli.moneybook.entity.Goods;
import com.arli.moneybook.repository.BuyOrderRepository;
import com.arli.moneybook.repository.GoodsRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@RestController
@RequestMapping("/buy")
@Transactional
public class BuyController {

    @Autowired
    private BuyOrderRepository orderRepository;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private Mapper beanMapper;

    @RequestMapping("/save")
    @ResponseBody
     public ApiResult saveBuyOrder(@Validated @RequestBody BuyOrderRequest buyOrderRequest)
     {
         BuyOrder buyOrder = beanMapper.map(buyOrderRequest, BuyOrder.class);
         buyOrder.setSinglePrice(new BigDecimal(buyOrder.getTotalPrice()).divide(new BigDecimal(buyOrder.getNumber())).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
         buyOrder.setOrderType("1");
         if(buyOrder.getOrderId()!=null )
         {
             BuyOrder oldOrder=orderRepository.findOne(buyOrder.getOrderId());
             Goods goods = goodsRepository.findOneByGoodsName(oldOrder.getGoodsName());
             goods.setGoodsCount(goods.getGoodsCount()-oldOrder.getNumber());
             goodsRepository.save(goods);
         }
         orderRepository.save(buyOrder);
         Goods newGoods = goodsRepository.findOneByGoodsName(buyOrder.getGoodsName());
         newGoods= (newGoods==null)? new Goods(buyOrder.getGoodsName()):newGoods;
         newGoods.setGoodsCount(newGoods.getGoodsCount()+buyOrder.getNumber());
         goodsRepository.save(newGoods);
         return  ApiResult.success("/buy/save","succuss");
     }

     @RequestMapping("/list")
     @ResponseBody
    public  ApiResult<Page<BuyOrder>> getBuyOrder(@RequestParam Integer pageNo,@RequestParam Integer pageSize)
     {
         Page<BuyOrder> all = orderRepository.findAll(new PageRequest(pageNo,pageSize, Sort.Direction.DESC,"createAt"));
         return  ApiResult.success("/buy/list",all);
     }

    @RequestMapping("/remove")
    @ResponseBody
    public  ApiResult<String> removeBuyOrder(@RequestParam Integer orderId)
    {
        BuyOrder buyOrder = orderRepository.findOne(orderId);
        Goods goods = goodsRepository.findOneByGoodsName(buyOrder.getGoodsName());
        goods.setGoodsCount(goods.getGoodsCount()-buyOrder.getNumber());
        goodsRepository.save(goods);
        orderRepository.delete(orderId);
        return  ApiResult.success("/buy/remove","删除成功");
    }

}
