package com.boss.train.shopcart.dao;


import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    int addGood(Goods goods);

    int addOrder(Order order);

    int addOrderItem(OrderItem orderItem);

    List<Goods> getAllGoods();

    Goods selectGoodById(int goodId);

    List<Order> selectOrderByUid(int userId);

    int updateGoodNumberById(int goodId, int goodNumber);
}
