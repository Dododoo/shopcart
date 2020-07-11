package com.boss.train.shopcart.controller;

import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.service.CartOperation;
import com.boss.train.shopcart.service.Impl.CartOperationImpl;
import com.boss.train.shopcart.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@ResponseBody
public class CartController{


    @Autowired
    private CartOperationImpl cartOperationImpl;

    @Autowired
    private CartUtil cartUtil;


    /**
     * 根据商品的id查询商品
     * */
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String getGoodById(){
        Goods good = new Goods();
        Map<String, Object> map = new HashMap<>();
        good = cartOperationImpl.selectGoodById(1);
        map.put("good",good);
        return cartUtil.getJsonString(map);
    }


    /**
     * 查询所有商品
     * */
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public String getAllGoods(){
        List<Goods> goodsList = new ArrayList<>();
        goodsList = cartOperationImpl.getAllGoods();
        Map<String, Object> map = new HashMap<>();
        for(Goods goods: goodsList){
            map.put(String.valueOf(goods.getGoodId()),goods);
        }
        return cartUtil.getJsonString(map);
    }


    /**
     * 根据用户id查询用户订单，并根据用户订单获取对应的orderitem
     * */
    @RequestMapping(path = "/findOrder", method = RequestMethod.GET)
    public String findOrderByUid(){
        List<Order> order = cartOperationImpl.selectOrderByUid(1);
        System.out.println(order);
        Map<String, Object> orderMap = new HashMap<>();
        orderMap.put("user:2",order);
        return cartUtil.getJsonString(orderMap);
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addOrder(@PathVariable("goodId") int goodId, @PathVariable("number") int number){


        System.out.println( cartOperationImpl.addGoods(2, goodId, number));
        return "success";
    }



    /***
     * 测试环境是否成功搭建
     */
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

}
