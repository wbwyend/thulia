package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.entity.Shop;
import com.wyer.server.entity.User;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.service.GoodsService;
import com.wyer.server.service.GoodsServiceImpl;
import com.wyer.server.service.ShopServiceImpl;
import com.wyer.server.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 测试接口
     * @return null
     */
    @AuthAccess
    @RequestMapping
    public Result get() {
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
        return Result.success(user);
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
        return Result.success(shop);
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
