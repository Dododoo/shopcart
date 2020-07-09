package com.boss.train.shopcart.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Goods {

    private int goodId;

    private String goodName;

    private double goodPrice;

}
