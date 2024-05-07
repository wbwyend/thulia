package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.Order;
import com.wyer.server.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function: 订单接口类
 * Writer: wyer
 * Date: 2023/12/05 19:45
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderServiceImpl orderService;

    /**
     * 通过用户id获取订单
     * @param uid
     * @return List<Order>
     */
    @UserAccess
    @RequestMapping("/get")
    public Result get(@RequestBody Integer uid) {
        return Result.success(orderService.selectOrdersByUid(uid));
    }

    /**
     * 添加订单
     * @param order
     * @return Integer oid
     */
    @UserAccess
    @RequestMapping("/add")
    public Result add(@RequestBody Order order) {
        return Result.success(orderService.insertOrder(order));
    }

    /**
     * 删除订单
     * @param oid
     * @param uid
     * @return List<Order>
     */
    @UserAccess
    @RequestMapping("/delete")
    public Result delete(@RequestParam("oid") Integer oid, @RequestParam("uid") Integer uid) {
        return Result.success(orderService.delete(oid, uid));
    }

}
