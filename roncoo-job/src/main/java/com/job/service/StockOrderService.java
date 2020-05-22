package com.job.service;

import com.job.conf.MyRabbitMQConfig;
import com.job.dao.course.StockOrderDao;
import com.job.entity.StockOrder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StockOrderService {

    @Resource
    StockOrderDao stockOrderDao;

    /**
     * 监听订单消息队列，并消费
     *
     * @param stockOrder
     */
    @RabbitListener(queues = MyRabbitMQConfig.ORDER_QUEUE)
    public void createOrder(StockOrder stockOrder) {
        stockOrderDao.insert(stockOrder);
    }

}
