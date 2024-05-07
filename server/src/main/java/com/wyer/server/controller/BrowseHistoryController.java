package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.BrowseHistory;
import com.wyer.server.service.impl.BrowseHistoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
     * 添加浏览记录
     * @param browseHistory
     * @return
     */
    @AuthAccess
    @PostMapping(value = "/add")
    public Result add(@RequestBody BrowseHistory browseHistory) {
        browseHistoryService.add(browseHistory);
        return Result.success();
    }

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

    @ShopAccess
    @GetMapping(value = "/get/saler")
    public Result getSalerData(@PathParam("sid") Integer sid, @PathParam("saler") Integer saler) {
        return Result.success(browseHistoryService.getSaler(sid, saler));
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
