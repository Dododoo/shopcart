package com.boss.train.shopcart.service;

import com.boss.train.shopcart.entity.Goods;

import java.util.List;

public interface CartOperation {
    int addGood(Goods goods);

    List<Goods> getGoodsList();


}
