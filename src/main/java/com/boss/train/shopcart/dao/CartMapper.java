package com.boss.train.shopcart.dao;


import com.boss.train.shopcart.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    int addGood(Goods goods);

}
