package com.arli.moneybook.repository;

import com.arli.moneybook.entity.BuyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder,Integer>{

}
