package com.boss.train.shopcart.service.Impl;

import com.boss.train.shopcart.constant.CartConstant;
import com.boss.train.shopcart.dao.ScartMapper;
import com.boss.train.shopcart.entity.Scart;
import com.boss.train.shopcart.service.ScartService;
import com.boss.train.shopcart.util.CartUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScartServiceImpl implements ScartService {

    private Logger logger = LoggerFactory.getLogger(ScartServiceImpl.class);

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private CartUtil cartUtil;

    @Autowired
    private ScartMapper scartMapper;

    @Autowired
    HttpSession session;

    /**
     *用户购物车下单业务逻辑实现
     *  判断购物车是否已经下过单导致目前购物过期
     *  1 过期，则使用UUID生成随机购物车id
     *  2 未过期，使用当前购物车的id
     *  将商品添加到购物车
     * */
    @Override
    public int addScart(int userId, int goodId, int goodNumber) {
        String scartId = "";
        Scart scart = new Scart();


        //判断用户未失效的购物车是否存在
        List<Scart> scartList = scartMapper.getScartByUid(userId, CartConstant.UNEXPIRE_STATUS);
        System.out.println(scartList.size());

        if(scartList.size() == 0){
            //不存在的情况
            //购物车不存在则随机生成购物车id
            scartId = cartUtil.generateUUID();

        }else{
            //购物车存在
            //购物车存在直接获取购物id
            scartId = scartList.get(0).getScartId();

            //遍历该用户的购物车查看是否已经存在该商品
            for(Scart scart1 :scartList){
                //商品存在修改商品数量
                if(scart1.getGoodId() == goodId){
                    goodNumber += scart1.getGoodNumber();
                    return scartMapper.updateGoodNumber(scartId, goodId, goodNumber);
                }
            }

        }

        //商品不存在添加商品到购物车
        scart.setScartId(scartId);
        scart.setGoodId(goodId);
        scart.setGoodNumber(goodNumber);
        scart.setStatus(CartConstant.UNEXPIRE_STATUS);
        scart.setUserId(userId);
        return scartMapper.addScart(scart);
    }

    /**
     * 根据用户Id查找该用户的购物车
     *  判断session里是否已经存在该用户的购物车
     *  1.1 存在：直接从session获取该用户的购物车
     *  1.2 不存在：从数据库读取该用户的购物车，并存入session
     */
    @Override
    public List<Scart> getScartByUid(int userId) {
        List<Scart> list = new ArrayList<>();

        if(httpSession.getAttribute("mycart:" + userId) == null){
            list = scartMapper.getScartByUid(userId, CartConstant.UNEXPIRE_STATUS);
            httpSession.setAttribute("mycart:" + userId, list);
            logger.info("session里没有，从数据库读取："+ list);
            return list;
        }else{
            list = (List<Scart>) httpSession.getAttribute("mycart:" + userId);
            logger.info("session里已经有了：" + list);
        }
        return list;
    }

    @Override
    public int removeGoodById(String scartId, int goodId) {
        return scartMapper.removeGoodById(scartId, goodId);
    }
}


