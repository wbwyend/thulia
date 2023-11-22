package com.wyer.server.service;

import com.wyer.server.entity.Order;

import java.util.List;

/**
 * Function: 订单服务类
 * Writer: wyer
 * Date: 2023/12/05 16:52
 **/
public interface OrderService {

    Order selectOrderByOid(Integer oid);

    void updateOrder(Integer oid, String end_time, String alipay_trade_no);

    List<Order> selectOrdersByUid(Integer uid);

    Object insertOrder(Order order);

    List<Order> delete(Integer oid, Integer uid);
}
