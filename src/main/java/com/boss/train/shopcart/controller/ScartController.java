package com.boss.train.shopcart.controller;

import com.boss.train.shopcart.entity.Scart;
import com.boss.train.shopcart.service.ScartService;
import com.boss.train.shopcart.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@RequestMapping("/scart")
public class ScartController {

    @Autowired
    private ScartService scartService;

    @Autowired
    private CartUtil cartUtil;

    @RequestMapping("/addToCart")
    public String addToCart(@RequestParam("goodId") int goodId, @RequestParam("number") int number){
        int temp = scartService.addScart(1, goodId, number);
        String msg = "";
        if(temp == 0 ){
            msg = "添加商品失败";
        }else{
            msg = "添加商品成功";
        }
        return cartUtil.getJsonString(6, msg);
    }

    @RequestMapping(value = "/removeGoods", method = RequestMethod.GET)
    public String removeGoods(int goodId){
        List<Scart> scartList = scartService.getScartByUid(2);
        String scartId = scartList.get(0).getScartId();
        int temp = scartService.removeGoodById(scartId, goodId);
        String msg = "";

        if(temp == 0 ){
            msg = "移除失败";
        }else{
            msg = "移除成功";
        }
        return cartUtil.getJsonString(7, msg);
    }

    @RequestMapping(value = "/getScart", method = RequestMethod.GET)
    public String getScartById(){
        List<Scart> scartList = scartService.getScartByUid(1);
        Map<String, Object> scartMap = new HashMap<>();
        for(Scart scart : scartList){
            scartMap.put("商品:" + scart.getGoodId(), scart);
        }
        return cartUtil.getJsonString(8, "用户" + 1 + "的购物车", scartMap);
    }
}
