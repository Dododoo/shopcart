package com.boss.train.shopcart.service;

import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.entity.OrderItem;

import java.util.List;

public interface CartOperation {
    int addGoods(int userId, int goodId, int goodNumber);

    List<Goods> getAllGoods();

    Goods selectGoodById(int id);

    List<Order> selectOrderByUid(int userId);

}
