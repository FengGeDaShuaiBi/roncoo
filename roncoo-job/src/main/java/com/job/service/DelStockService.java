package com.job.service;

import com.job.conf.MyRabbitMQConfig;
import com.job.dao.course.StockDao;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 18116
 */
@Service
public class DelStockService {

    @Resource
    StockDao stockDao;


    /**
     * 监听库存消息队列，并消费
     *
     * @param stockName
     */
    @RabbitListener(queues = MyRabbitMQConfig.STORY_QUEUE)
    public void decrByStock(String stockName) {
        System.err.println("库存消息队列收到的消息商品信息是：" + stockName);
        stockDao.delStockOne(stockName);
    }

}
