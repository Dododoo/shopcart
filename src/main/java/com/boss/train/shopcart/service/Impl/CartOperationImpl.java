package com.boss.train.shopcart.service.Impl;

import com.boss.train.shopcart.dao.CartMapper;
import com.boss.train.shopcart.entity.Goods;
import com.boss.train.shopcart.entity.Order;
import com.boss.train.shopcart.entity.OrderItem;
import com.boss.train.shopcart.service.CartOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CartOperationImpl implements CartOperation {

    @Autowired
    private CartMapper cartMapper;


    /**
     *用户直接下单业务逻辑实现
     * */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int addGoods(int userId, int goodId, int goodNumber) {
        if(goodId == 0 ){
            return -1;
        }
        //用户添加的数量大于库存时，添加失败
        Goods good = cartMapper.selectGoodById(goodId);
        if(good.getGoodNumber() < goodNumber){
            return -1;
        }

        //添加到订单
        Order order = new Order();
        order.setUserId(userId);
        cartMapper.addOrder(order);
        int orderId = order.getOrderId();

        //添加到orderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodId(goodId);
        orderItem.setOrderId(orderId);
        orderItem.setNumber(goodNumber);
        cartMapper.addOrderItem(orderItem);

        //int i = 1 / 0;

        //添加商品到订单后修改库存数量
        int number = good.getGoodNumber() - goodNumber;
        System.out.println(number);
        int code = cartMapper.updateGoodNumberById(goodId, number);

        return code;
    }


    @Override
    public List<Goods> getAllGoods() {
        return cartMapper.getAllGoods();
    }

    @Override
    public Goods selectGoodById(int goodId) {
        return cartMapper.selectGoodById(goodId);
    }

    @Override
    public List<Order> selectOrderByUid(int userId) {
        return cartMapper.selectOrderByUid(userId);
    }

}
