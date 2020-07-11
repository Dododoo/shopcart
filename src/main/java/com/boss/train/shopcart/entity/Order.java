package com.boss.train.shopcart.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class Order {
    private int orderId;

    private int userId;

    private List<OrderItem> list;

}
