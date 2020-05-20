package com.system.order.service;

import com.model.generator.mapper.OrderInfoMapper;
import com.model.generator.pojo.OrderInfo;
import com.model.generator.pojo.OrderInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderInfoMapper orderInfoMapper;

    public int createOrder(OrderInfo orderInfo) {
        return orderInfoMapper.insert(orderInfo);
    }

    public int updateOrder(OrderInfo orderInfo, OrderInfoExample example) {
        return orderInfoMapper.updateByExampleSelective(orderInfo, example);
    }


}
