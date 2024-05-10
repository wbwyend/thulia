package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.model.entity.Shop;
import com.wyer.server.model.vo.Password;
import com.wyer.server.service.impl.ShopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function: 商家接口类
 * Writer: wyer
 * Date: 2023/10/01 15:54
 **/
@RestController
@RequestMapping(value = "/shop")
public class ShopController {

    @Autowired
    ShopServiceImpl shopService;

    /**
     * 商家修改商家信息
     * @param shop
     * @return Shop
     */
    @ShopAccess
    @PostMapping(value = "/modify/information")
    public Result modifyInformation(@RequestBody Shop shop) {
        return Result.success(shopService.modify(shop));
    }

    /**
     * 商家修改密码
     * @param password
     * @return Shop
     */
    @ShopAccess
    @PostMapping(value = "/modify/password")
    public Result modifyPassword(@RequestBody Password password) {
        return Result.success(shopService.modify(password));
    }

    /**
     * 商家获取商家信息
     * @param sid
     * @return
     */
    @AuthAccess
    @PostMapping(value = "/get/details")
    public Result getDetailsPageShopInformation(@RequestBody(required = false) Integer sid) {
        return Result.success(shopService.getDetailsPageShopInformation(sid));
    }
}
