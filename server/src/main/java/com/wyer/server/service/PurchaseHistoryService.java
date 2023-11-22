package com.wyer.server.service;

import com.wyer.server.common.PurchaseHistoryForShopView;
import com.wyer.server.common.PurchaseHistoryForUserPay;
import com.wyer.server.common.PurchaseHistoryForUserView;

import java.util.List;

/**
 * Function: 购买记录服务类
 * Writer: wyer
 * Date: 2023/12/05 21:16
 **/
public interface PurchaseHistoryService {
    List<PurchaseHistoryForUserPay> selectPurchaseHistoryByOid(Integer oid);

    List<PurchaseHistoryForUserView> selectPurchaseHistoryByUid(Integer uid);

    List<PurchaseHistoryForShopView> selectPurchaseHistoryBySid(Integer sid);
}
