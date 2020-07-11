package com.boss.train.shopcart.entity;

import lombok.Data;

@Data
public class OrderItem {
    private int orderId;

    private int goodId;

    private int number;
}
