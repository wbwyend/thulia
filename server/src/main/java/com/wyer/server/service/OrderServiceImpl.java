package com.wyer.server.service;

import com.wyer.server.entity.Order;
import com.wyer.server.entity.PurchaseHistory;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.mapper.OrderMapper;
import com.wyer.server.mapper.PurchaseHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Function: 订单服务类
 * Writer: wyer
 * Date: 2023/12/05 16:51
 **/
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    PurchaseHistoryMapper purchaseHistoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 通过订单id获取订单
     * @param oid
     * @return Order
     */
    @Override
    public Order selectOrderByOid(Integer oid) {
        return orderMapper.selectOrderByOid(oid);
    }

    /**
     * 支付完成后更新订单信息
     * @param oid
     * @param end_time
     * @param alipay_trade_no
     */
    @Override
    public void updateOrder(Integer oid, String end_time, String alipay_trade_no) {
        orderMapper.updateOrderCompleteByOid(oid, end_time, alipay_trade_no, "已完成");
        purchaseHistoryMapper.updateTimeByOid(oid, end_time);
        goodsMapper.updateSalesByOid(oid);
    }

    /**
     * 通过用户id获取订单
     * @param uid
     * @return List<Order>
     */
    @Override
    public List<Order> selectOrdersByUid(Integer uid) {
        return orderMapper.selectOrdersByUid(uid);
    }

    /**
     * 添加新订单
     * @param order
     * @return Object
     */
    @Override
    public Object insertOrder(Order order) {
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String formattedDateTime = currentTime.format(formatter);
            order.setCreate_time(formattedDateTime);
            order.setStatus("未支付");
            orderMapper.insertOrder(order);
            order.setOid(orderMapper.selectOrdersByUidAndCreateTime(order.getCreate_time(), order.getUid()));
            return order.getOid();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("系统错误");
        }
    }

    /**
     * 删除订单
     * @param oid
     * @param uid
     * @return List<Order>
     */
    @Override
    public List<Order> delete(Integer oid, Integer uid) {
        orderMapper.updateOrderStatusByOid(oid, "已删除");
        List<PurchaseHistory> purchaseHistoryList = purchaseHistoryMapper.selectPurchaseHistoryByOid(oid);
        goodsMapper.updateAddSurplusByDeleteOrder(purchaseHistoryList);
        return orderMapper.selectOrdersByUid(uid);
    }
}
