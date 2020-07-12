package com.boss.train.shopcart.util;

import com.alibaba.fastjson.JSONObject;
import com.boss.train.shopcart.entity.Goods;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CartUtil {

    public static String getJsonString(int code,String msg,Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null){
            for(String key : map.keySet()){
                json.put(key, map.get(key));
            }
        }
        return json.toJSONString();
    }


    public static String getJsonString(int code, String msg) {
        return getJsonString(code, msg, null);
    }

    public static String getJsonString(int code) {
        return getJsonString(code, null, null);
    }


}
