package com.boss.train.shopcart.util;

import com.boss.train.shopcart.entity.Scart;

public class RedisUtil {
    //分隔符
    private static final String SPLIT=":";
    //前缀
    private static final String PREFIX_ENTITY_LIKE="cart";

    //购物车实体
    //key:   cart:userId -> set(cart) 将userId对应用户的购物车保存下来
    public static String getEntityLikeKey(int userId){
        return PREFIX_ENTITY_LIKE + SPLIT + userId;
    }

}
