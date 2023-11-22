package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.service.BrowseHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function: 浏览记录接口类
 * Writer: wyer
 * Date: 2023/10/23 15:41
 **/
@RestController
@RequestMapping(value = "/browse")
public class BrowseHistoryController {

    @Autowired
    BrowseHistoryServiceImpl browseHistoryService;

    /**
     * 商家获取浏览记录统计
     * @param sid
     * @return BrowseHistoryCount
     */
    @ShopAccess
    @PostMapping(value = "/get/shop")
    public Result getShopData(@RequestBody Integer sid) {
        return Result.success(browseHistoryService.getShop(sid));
    }

    /**
     * 商家获取浏览记录详情
     * @param gid
     * @return BrowseHistoryDetails
     */
    @ShopAccess
    @PostMapping(value = "/get/shop/details")
    public Result getShopDetailsData(@RequestBody Integer gid) {
        return Result.success(browseHistoryService.getShopDetails(gid));
    }

    /**
     * 用户获取浏览记录
     * @param uid
     * @return BrowseHistoryDetails
     */
    @UserAccess
    @PostMapping(value = "/get/user")
    public Result getUserBrowseHistoryByUid(@RequestBody Integer uid) {
        return Result.success(browseHistoryService.selectBrowsHistoryDetailsForUserByUid(uid));
    }



}
