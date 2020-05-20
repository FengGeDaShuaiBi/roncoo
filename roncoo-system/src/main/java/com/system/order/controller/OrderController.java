package com.system.order.controller;

import com.model.dto.Dto;
import com.model.dto.DtoUtil;
import com.model.generator.pojo.OrderInfo;
import com.model.generator.pojo.OrderInfoExample;
import com.system.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api("订单测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "新增订单信息")
    @PostMapping("/create/order")
    public Dto createOrder(@RequestBody OrderInfo order) {
        int result = orderService.createOrder(order);
        if (result == 0)
            return DtoUtil.returnFail("新增失败", "10002");
        return DtoUtil.returnSuccess("新增成功");
    }

    @ApiOperation(value = "修改订单状态")
    @GetMapping("/update/order/status")
    public Dto updateOrderStatus(@RequestParam(value = "status_id", required = true) byte status_id,
                                 @RequestParam(value = "order_no", required = true) long order_no) {
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria criteria = example.createCriteria();
        criteria.andOrderNoEqualTo(order_no);
        OrderInfo info = new OrderInfo();
        info.setOrderStatus(status_id);
        int result = orderService.updateOrder(info, example);
        if (result == 0)
            return DtoUtil.returnFail("修改失败", "10002");
        return DtoUtil.returnDataSuccess("修改成功");
    }

}
