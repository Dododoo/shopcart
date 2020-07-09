package com.boss.train.shopcart.service.Impl;

import com.boss.train.shopcart.dao.CartMapper;
import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.service.CartOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartOperationImpl implements CartOperation {



    @Override
    public int addGood(Goods goods) {
        return 0;
    }

    @Override
    public List<Goods> getGoodsList() {
        return null;
    }
}
