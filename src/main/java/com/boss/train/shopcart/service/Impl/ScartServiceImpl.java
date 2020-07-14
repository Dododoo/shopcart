package com.boss.train.shopcart.service.Impl;

import com.boss.train.shopcart.constant.CartConstant;
import com.boss.train.shopcart.dao.ScartMapper;
import com.boss.train.shopcart.entity.Scart;
import com.boss.train.shopcart.service.ScartService;
import com.boss.train.shopcart.util.CartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ScartServiceImpl implements ScartService {

    @Autowired
    private CartUtil cartUtil;

    @Autowired
    private ScartMapper scartMapper;

    @Autowired
    HttpSession session;

    /**
     *用户购物车下单业务逻辑实现
     * */
    @Override
    public int addScart(int userId, int goodId, int goodNumber) {
        String scartId = "";
        Scart scart = new Scart();


        //判断用户未失效的购物车是否存在
        List<Scart> scartList = scartMapper.getScartByUid(userId, CartConstant.UNEXPIRE_STATUS);
        System.out.println(scartList.size());

        if(scartList.size() == 0){//不存在的情况
            //购物车不存在则随机生成购物车id
            scartId = cartUtil.generateUUID();
        }else{//购物车存在
            //购物车存在直接获取购物id
            scartId = scartList.get(0).getScartId();
            //遍历该用户的购物车查看是否已经存在该商品
            for(Scart scart1 :scartList){
                if(scart1.getGoodId() == goodId){
                    goodNumber += scart1.getGoodNumber();
                    return scartMapper.updateGoodNumber(scartId, goodId, goodNumber);
                }
            }
        }

        scart.setScartId(scartId);
        scart.setGoodId(goodId);
        scart.setGoodNumber(goodNumber);
        scart.setStatus(CartConstant.UNEXPIRE_STATUS);
        scart.setUserId(userId);
        return scartMapper.addScart(scart);
    }

    @Override
    public List<Scart> getScartByUid(int userId) {
        List<Scart> list = scartMapper.getScartByUid(userId, CartConstant.UNEXPIRE_STATUS);
        return list;
    }

    @Override
    public int removeGoodById(String scartId, int goodId) {
        return scartMapper.removeGoodById(scartId, goodId);
    }
}


