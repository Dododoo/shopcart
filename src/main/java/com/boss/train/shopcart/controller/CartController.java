package com.boss.train.shopcart.controller;

import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.service.Impl.CartOperationImpl;
import com.boss.train.shopcart.util.CartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Controller
@RequestMapping("/cart")
@ResponseBody
public class CartController {

    private ReentrantLock lock = new ReentrantLock();

    @Autowired
    private CartOperationImpl cartOperation;

    @Autowired
    private CartUtil cartUtil;

    private Logger logger = LoggerFactory.getLogger(CartController.class);


    /**
     * 根据商品的id查询商品
     * */
    @RequestMapping(path = "/getSingleGoods", method = RequestMethod.GET)
    public String getGoodById(@RequestParam("goodId")int goodId){
        Goods good = new Goods();
        Map<String, Object> map = new HashMap<>();
        good = cartOperation.selectGoodById(goodId);
        logger.info(" goodId:"+goodId);
        map.put("good",good);
        return cartUtil.getJsonString(0, "查询单个商品成功", map);
    }

    /**
     * 查询所有商品
     * */
    @RequestMapping(path = "/getAllGoods", method = RequestMethod.GET)
    public String getAllGoods(){
        List<Goods> goodsList = new ArrayList<>();
        goodsList = cartOperation.getAllGoods();
        Map<String, Object> map = new HashMap<>();
        for(Goods goods: goodsList){
            map.put(String.valueOf(goods.getGoodName()),goods);
        }

        return cartUtil.getJsonString(1, "查询所有商品成功", map);
    }


    /**
     * 根据用户id查询用户订单，并根据用户订单获取对应的orderitem
     * */
    @RequestMapping(path = "/findOrder", method = RequestMethod.GET)
    public String findOrderByUid(){
        List<Order> order = cartOperation.selectOrderByUid(1);

        Map<String, Object> orderMap = new HashMap<>();
        for(Order order1 : order){
            orderMap.put("order:" + order1.getOrderId(),order1);
        }
        return cartUtil.getJsonString(3, "查询订单成功", orderMap);
    }


    /**
     * 用户直接下单
     * */
    @RequestMapping(path = "/addOrder", method = RequestMethod.POST)
    public String addOrder(@RequestParam("goodId")int goodId, @RequestParam("number")int number){
        String msg = "";

        //在添加商品到订单时加锁
        lock.lock();
        int temp = 0;
        try{
            temp = cartOperation.addGoods(2, goodId, number);
        }finally {
            //添加完毕释放锁
            lock.unlock();
        }
        System.out.println(temp);
        if(temp == -1){
            msg = "下单失败";
        }else {
            msg = "下单成功";
        }
        return cartUtil.getJsonString(4, msg);
    }

    /***
     * 测试环境是否成功搭建
     */
    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }

}
