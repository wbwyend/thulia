package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.*;
import com.wyer.server.service.impl.*;
import com.wyer.server.utils.IPv4Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * Function: 登录与注册接口类
 * Writer: wyer
 * Date: 2023/09/16 16:58
 **/
@RestController
public class WebController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    ShopServiceImpl shopService;

    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    SalerServiceImpl salerService;

    @Autowired
    BigDataServiceImpl bigDataService;

    /**
     * 测试接口
     * @return null
     */
    @AuthAccess
    @RequestMapping
    public Result get() {
        String rootPath = System.getProperty("user.dir") + File.separator + "files";
        return Result.success(rootPath);
    }


    @AuthAccess
    @PostMapping(value = "/test/bd")
    public Result test(@RequestBody Integer seconds) {
        System.out.println(seconds);
        return Result.success();
    }

    /**
     * 销售登录
     * @param saler
     * @return
     */
    @AuthAccess
    @PostMapping(value = "/login/saler")
    public Result post(@RequestBody Saler saler) {
        if (saler.getUsername() == null || saler.getPassword() == null) {
            return Result.error("账号或密码错误");
        }
        saler = salerService.login(saler);
        SalerOperation salerOperation = new SalerOperation();
        salerOperation.setSalerId(saler.getId());
        salerOperation.setIp(IPv4Util.getRequestIp());
        salerOperation.setType("li");
        salerOperation.setTime(System.currentTimeMillis());
        bigDataService.saveSalerOperation(salerOperation);
        return Result.success(saler);
    }

    @ShopAccess
    @PostMapping(value = "/logout/saler")
    public Result logoutSaler(@RequestBody Integer salerId) {
        SalerOperation salerOperation = new SalerOperation();
        salerOperation.setIp(IPv4Util.getRequestIp());
        salerOperation.setTime(System.currentTimeMillis());
        salerOperation.setSalerId(salerId);
        salerOperation.setType("lo");
        bigDataService.saveSalerOperation(salerOperation);
        return Result.success();
    }

    /**
     * 用户登录接口
     * @param user
     * @return User
     */
    @AuthAccess
    @PostMapping(value = "/login")
    public Result login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("账号或密码错误");
        }
        user = userService.login(user);
        LoginData loginData = new LoginData();
        loginData.setIp(IPv4Util.getRequestIp());
        loginData.setTime(System.currentTimeMillis());
        loginData.setUid(user.getUid());
        loginData.setType("li");
        bigDataService.saveLoginData(loginData);
        return Result.success(user);
    }

    @UserAccess
    @PostMapping(value = "/logout")
    public Result logout(@RequestBody Integer uid) {
        LoginData loginData = new LoginData();
        loginData.setIp(IPv4Util.getRequestIp());
        loginData.setTime(System.currentTimeMillis());
        loginData.setUid(uid);
        loginData.setType("lo");
        bigDataService.saveLoginData(loginData);
        return Result.success();
    }

    /**
     * 用户注册接口
     * @param user
     * @return null
     */
    @AuthAccess
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return Result.error("系统错误");
        }
        try {
            userService.register(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("账号已存在");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 商家登录接口
     * @param shop
     * @return Shop
     */
    @AuthAccess
    @PostMapping(value = "login/shop")
    public Result shopLogin(@RequestBody Shop shop) {
        if (shop.getUsername() == null || shop.getPassword() == null) {
            return Result.error("账号或密码错误");
        }
        shop = shopService.login(shop);
        ShopOperation shopOperation = new ShopOperation();
        shopOperation.setSid(shop.getSid());
        shopOperation.setIp(IPv4Util.getRequestIp());
        shopOperation.setType("li");
        shopOperation.setTime(System.currentTimeMillis());
        bigDataService.saveShopOperation(shopOperation);
        return Result.success(shop);
    }

    @ShopAccess
    @PostMapping(value = "/logout/shop")
    public Result logoutShop(@RequestBody Integer sid) {
        ShopOperation shopOperation = new ShopOperation();
        shopOperation.setIp(IPv4Util.getRequestIp());
        shopOperation.setTime(System.currentTimeMillis());
        shopOperation.setSid(sid);
        shopOperation.setType("lo");
        bigDataService.saveShopOperation(shopOperation);
        return Result.success();
    }

    /**
     * 商家注册接口
     * @param shop
     * @return null
     */
    @AuthAccess
    @PostMapping(value = "/register/shop")
    public Result shopRegister(@RequestBody Shop shop) {
        if (shop.getUsername() == null || shop.getPassword() == null || shop.getEmail() == null
                || shop.getShopname() == null || shop.getPhone() == null) {
            return Result.error("系统错误");
        }
        try {
            shopService.register(shop);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("账号已存在");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 搜索商品
     * @param text
     * @return List<Goods>
     */
    @AuthAccess
    @PostMapping(value = "/search")
    public Result search(@RequestBody String text) {
        return Result.success(goodsService.search(text));
    }



}
