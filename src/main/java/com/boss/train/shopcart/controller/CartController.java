package com.boss.train.shopcart.controller;

import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.service.CartOperation;
import com.boss.train.shopcart.service.Impl.CartOperationImpl;
import com.boss.train.shopcart.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class CartController{


    @Autowired
    private CartOperationImpl cartOperationImpl;

    @Autowired
    private CartUtil cartUtil;


    @RequestMapping(path = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String getGoodById(){
        Goods good = new Goods();
        Map<String, Goods> map = new HashMap<>();
        good = cartOperationImpl.selectGoodById(1);
        map.put("good",good);

        return cartUtil.getJsonString(map);
    }


    /***
     * 测试环境是否成功搭建
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello world";
    }

}
