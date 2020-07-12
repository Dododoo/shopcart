package com.boss.train.shopcart.controller;

import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.service.CartOperation;
import com.boss.train.shopcart.service.Impl.CartOperationImpl;
import com.boss.train.shopcart.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        return cartUtil.getJsonString(0, "查询单个商品成功", map);
    }


    /**
     * 查询所有商品
     * */
    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public String getAllGoods(Model modelAndView){
        List<Goods> goodsList = new ArrayList<>();
        goodsList = cartOperationImpl.getAllGoods();
        Map<String, Object> map = new HashMap<>();
        for(Goods goods: goodsList){
            map.put(String.valueOf(goods.getGoodId()),goods);
        }

        return cartUtil.getJsonString(1, "查询所有商品成功", map);
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
        return cartUtil.getJsonString(3, "查询订单成功", orderMap);
    }


    /**
     * 用户添加商品到订单
     * */
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addOrder(@RequestParam("goodId")int goodId, @RequestParam("number")int number){
        String msg = "";
        int temp = cartOperationImpl.addGoods(2, goodId, number);
        System.out.println(temp);
        if(temp == -1){
            msg = "添加到订单失败";
        }else {
            msg = "添加到订单成功";
        }

        return cartUtil.getJsonString(4, msg);
    }

    /***
     * 测试环境是否成功搭建
     */
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

}
