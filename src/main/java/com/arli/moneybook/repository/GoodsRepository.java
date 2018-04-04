package com.arli.moneybook.repository;

import com.arli.moneybook.entity.BuyOrder;
import com.arli.moneybook.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods,Integer>{
    Goods findOneByGoodsName(String goodsName);
    List<Goods> findAllByGoodsCountGreaterThan(Integer count);
}
