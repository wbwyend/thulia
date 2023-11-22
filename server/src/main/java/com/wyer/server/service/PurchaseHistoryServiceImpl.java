package com.wyer.server.service;

import com.wyer.server.common.PurchaseHistoryForShopView;
import com.wyer.server.common.PurchaseHistoryForUserPay;
import com.wyer.server.common.PurchaseHistoryForUserView;
import com.wyer.server.entity.PurchaseHistory;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.mapper.PurchaseHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function: 购买记录服务类
 * Writer: wyer
 * Date: 2023/12/05 21:17
 **/
@Service
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    @Autowired
    PurchaseHistoryMapper purchaseHistoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 批量添加购买记录
     * @param list
     * @return Object
     */
    public Object batchInsertPurchaseHistory(List<PurchaseHistory> list) {
        purchaseHistoryMapper.batchInsertPurchaseHistory(list);
        goodsMapper.updateSurplusByPurchase(list);
        return null;
    }

    /**
     * 通过订单id获取购买记录
     * @param oid
     * @return List<PurchaseHistoryForUserPay>
     */
    @Override
    public List<PurchaseHistoryForUserPay> selectPurchaseHistoryByOid(Integer oid) {
        return purchaseHistoryMapper.selectPurchaseHistoryForUserPayByOid(oid);
    }

    /**
     * 通过用户id获取购买记录
     * @param uid
     * @return List<PurchaseHistoryForUserView>
     */
    @Override
    public List<PurchaseHistoryForUserView> selectPurchaseHistoryByUid(Integer uid) {
        return purchaseHistoryMapper.selectPurchaseHistoryByUid(uid);
    }

    /**
     * 通过商家id获取购买记录
     * @param sid
     * @return List<PurchaseHistoryForShopView>
     */
    @Override
    public List<PurchaseHistoryForShopView> selectPurchaseHistoryBySid(Integer sid) {
        return purchaseHistoryMapper.selectPurchaseHistoryBySid(sid);
    }
}
