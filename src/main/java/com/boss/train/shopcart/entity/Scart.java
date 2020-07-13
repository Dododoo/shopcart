package com.boss.train.shopcart.entity;

import lombok.Data;

@Data
public class Scart {
    private String scartId;

    private int userId;

    private int goodId;

    private int goodNumber;

    private int status;
}
