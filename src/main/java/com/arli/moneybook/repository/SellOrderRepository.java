package com.arli.moneybook.repository;

import com.arli.moneybook.entity.BuyOrder;
import com.arli.moneybook.entity.SellOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellOrderRepository extends JpaRepository<SellOrder,Integer>{

}
