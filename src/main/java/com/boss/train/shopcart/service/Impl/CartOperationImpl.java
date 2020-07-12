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
     *
     * */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public int addGoods(int userId, int goodId, int goodNumber) {
        if(goodId == 0 ){
            return 0;
        }
        Order order = new Order();
        order.setUserId(userId);
        //添加到订单
        cartMapper.addOrder(order);
        int orderId = order.getOrderId();

        //添加到orderItem
        OrderItem orderItem = new OrderItem();
        orderItem.setGoodId(goodId);
        orderItem.setOrderId(orderId);
        orderItem.setNumber(goodNumber);
        cartMapper.addOrderItem(orderItem);

        return order.getOrderId();
    }

    @Override
    public int updateGoodsNumber(int userId, int goodId) {
        return 0;
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

    @Override
    public OrderItem getOrderItemByOId(int orderId) {
        return cartMapper.getOrderItemByOId(orderId);
    }
}
