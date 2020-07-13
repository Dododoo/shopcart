package com.boss.train.shopcart.dao;

import com.boss.train.shopcart.constant.CartConstant;
import com.boss.train.shopcart.entity.Scart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScartMapper {

    List<Scart> getScartByUid(int userId, int status);

    int addScart(Scart scart);

    int removeGoodById(String scartId, int goodId);

    int updateGoodNumber(String scartId, int goodId, int goodNumber);

}
