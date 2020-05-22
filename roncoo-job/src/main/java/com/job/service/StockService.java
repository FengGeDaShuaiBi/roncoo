package com.job.service;

import com.job.dao.course.StockDao;
import com.job.dao.course.StockOrderDao;
import com.job.entity.Stock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StockService {

    @Resource
    StockDao stockDao;

    @Resource
    StockOrderDao stockOrderDao;

    @Resource
    RedisTemplate redisTemplate;

    //查询商品是否存在
    public List<Stock> getStockAll(String stockName) {
        Stock stock = new Stock();
        stock.setName(stockName);
        return stockDao.queryAll(stock);
    }

    // 秒杀商品后减少库存
    public void delStock(String stockName) {
        Stock stock = new Stock();
        stock.setName(stockName);
        List<Stock> list = stockDao.queryAll(stock);
        if (list.size() != 0) {
//            System.err.println("商品ID>>>>>" + list.get(0));
            stockDao.delStockOne(stockName);
        } else {
            System.err.println("商品不存在");
        }
    }

    // 秒杀商品前判断是否有库存
    public Integer selectStock(String stockName) {
        Stock stock = new Stock();
        stock.setName(stockName);
        List<Stock> list = stockDao.queryAll(stock);
        if (list.size() != 0) {
//            System.err.println("库存为>>>>>" + list.get(0).getStock().intValue());
            return list.get(0).getStock().intValue();
        }
        return 0;
    }

}
