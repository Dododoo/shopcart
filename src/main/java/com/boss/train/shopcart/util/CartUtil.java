package com.boss.train.shopcart.util;

import com.alibaba.fastjson.JSONObject;
import com.boss.train.shopcart.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartUtil {

    public String getJsonString(Map<String, Goods> map){
        JSONObject json = new JSONObject();
        if(map != null){
            for(String key : map.keySet()){
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }
}
