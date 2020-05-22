package com.job.controller;


import com.job.conf.*;
import com.job.dao.course.StockDao;
import com.job.dao.course.StockOrderDao;
import com.job.entity.Stock;
import com.job.entity.StockOrder;
import com.job.service.DelStockService;
import com.job.service.StockOrderService;
import com.job.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api(tags = "抢购接口")
@RequestMapping("/api/design/stock")
public class StockRabbitController {

    @Autowired
    StockDao stockDao;
    @Autowired
    StockOrderDao stockOrderDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisService redisService;
    @Autowired
    private StockOrderService stockOrderService;
    @Autowired
    private DelStockService delStockService;
    @Resource
    StockService stockService;


    @ApiOperation("/秒杀")
    @PostMapping("/setStockOne")
    public DTO sec(@RequestParam(value = "userId", required = true) Integer userId,
                   @RequestParam(value = "stockId", required = true) Integer stockId) {
        String message = null;
        System.err.println("参加秒杀的用户是" + userId + "\t" + "秒杀的商品是" + stockId);
        Boolean result = redisService.exists("秒杀商品:" + stockId);
        if (!result) {
            return DtoUtil.returnSuccess("商品不存在");
        } else {
            //调用redis给相应商品库存量减一
            System.out.println(redisService.get("秒杀商品:" + stockId, Integer.class));
            Long decrByResult = redisService.decrBy("秒杀商品:" + stockId);
            //判断库存
            if (decrByResult >= 0) {
                System.err.println("用户" + userId + "秒杀" + stockId + "库存足够");
                //发消息给库存消息队列，将库存数据减一
                rabbitTemplate.convertAndSend(MyRabbitMQConfig.STORY_EXCHANGE, MyRabbitMQConfig.STORY_ROUTING_KEY, stockId);
                //发消息给订单消息队列，创建订单
                StockOrder stockOrder = new StockOrder();
                stockOrder.setStockId(stockId);
                stockOrder.setOrderStatus(0);
                stockOrder.setCreate(new Date());
                stockOrder.setUserId(userId);
                boolean redisKey = redisService.exists("秒杀用户:" + String.valueOf(stockId) + String.valueOf(userId));
                if (redisKey) {
                    return DtoUtil.returnSuccess("您已经参加过抢购");
                }
                redisService.put("秒杀用户:" + String.valueOf(stockId) + String.valueOf(userId), String.valueOf(stockId) + String.valueOf(userId), 1000 * 1000);
                rabbitTemplate.convertAndSend(MyRabbitMQConfig.ORDER_EXCHANGE, MyRabbitMQConfig.ORDER_ROUTING_KEY, stockOrder);
            } else {
                System.err.println("用户" + userId + "秒杀" + stockId + "库存没有剩余");
                message = "用户：" + userId + "商品的库存量没有剩余,秒杀结束";
            }
        }
        return DtoUtil.returnSuccess("秒杀成功");
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void falseOrderStatus() {
        List<StockOrder> list = stockOrderDao.getOrderByTime();
        for (StockOrder order : list
        ) {
            stockOrderDao.updateOrderStatusById(order.getId(), 2);
            stockDao.updateStock(order.getStockId());
        }
        System.out.println(list.size() + "订单超时未支付已经取消");
    }


    @ApiOperation(value = "初始化所有商品进redis")
    @GetMapping("/initialize/stock")
    public DTO initializeStock() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<Stock> stockList = stockDao.queryStockAll();
        if (EmptyUtils.isEmpty(stockList)) {
            return DtoUtil.returnSuccess("当前没有秒杀商品");
        }
        for (Stock stock : stockList
        ) {
            Date d1 = df.parse(df1.format(stock.getEndTime()));
            Date d2 = df.parse(df1.format(stock.getStartTime()));
            long diff = d1.getTime() - d2.getTime();
            long seconds = diff / 1000;
            redisService.put("秒杀商品:" + stock.getId(), stock.getStock(), seconds);
        }
        return DtoUtil.returnSuccess("初始化成功");
    }
}
