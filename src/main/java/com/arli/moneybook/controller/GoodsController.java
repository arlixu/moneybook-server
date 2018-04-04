package com.arli.moneybook.controller;

import com.arli.moneybook.dto.ApiResult;
import com.arli.moneybook.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsRepository goodsRepository;

    //商品列表
    @RequestMapping("/list")
    @ResponseBody
    public ApiResult listGoods()
    {
         return  ApiResult.success("goods/list",goodsRepository.findAllByGoodsCountGreaterThan(0));
    }

    //库存列表
    @RequestMapping("/listByPage")
    @ResponseBody
    public ApiResult listGoodsByPage(Integer pageNo,Integer pageSize)
    {
        return  ApiResult.success("goods/listByPage",goodsRepository.findAll(new PageRequest(pageNo,pageSize, Sort.Direction.DESC,"goodsCount")));
    }
}
