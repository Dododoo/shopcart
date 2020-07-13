package com.boss.train.shopcart.service;

import com.boss.train.shopcart.entity.Scart;

import java.util.List;

public interface ScartService {

    int addScart(int userId, int goodId, int goodNumber);

    List<Scart> getScartByUid(int userId);

    int removeGoodById(String scartId, int goodId);
}
