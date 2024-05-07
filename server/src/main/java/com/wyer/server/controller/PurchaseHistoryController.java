package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.PurchaseHistory;
import com.wyer.server.service.impl.PurchaseHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Function: 购买记录接口类
 * Writer: wyer
 * Date: 2023/12/05 21:15
 **/
@RestController
@RequestMapping("/purchase")
public class PurchaseHistoryController {

    @Autowired
    PurchaseHistoryServiceImpl purchaseHistoryService;

    /**
     * 批量添加购买记录
     * @param list
     * @return Result
     */
    @UserAccess
    @PostMapping("/add")
    public Result add(@RequestBody List<PurchaseHistory> list) {
        return Result.success(purchaseHistoryService.batchInsertPurchaseHistory(list));
    }

    /**
     * 通过订单id查看购买记录
     * @param oid
     * @return List<PurchaseHistoryForUserPay>
     */
    @UserAccess
    @PostMapping("/select")
    public Result select(@RequestBody Integer oid) {
        return Result.success(purchaseHistoryService.selectPurchaseHistoryByOid(oid));
    }

    /**
     * 通过用户id查看购买记录
     * @param uid
     * @return  List<PurchaseHistoryForUserView>
     */
    @UserAccess
    @PostMapping("/get/user")
    public Result selectUserByUid(@RequestBody Integer uid) {
        return Result.success(purchaseHistoryService.selectPurchaseHistoryByUid(uid));
    }

    /**
     * 通过商家id查看购买记录
     * @param sid
     * @return  List<PurchaseHistoryForShopView>
     */
    @ShopAccess
    @PostMapping("/get/shop")
    public Result selectShopBySid(@RequestBody Integer sid) {
        return Result.success(purchaseHistoryService.selectPurchaseHistoryBySid(sid));
    }

    /**
     * 过销售id查看购买记录
     * @param id
     * @return
     */
    @ShopAccess
    @PostMapping("/get/saler")
    public Result selectShopBySalerId(@RequestBody Integer id) {
        return Result.success(purchaseHistoryService.selectPurchaseHistoryBySalerId(id));
    }

}
