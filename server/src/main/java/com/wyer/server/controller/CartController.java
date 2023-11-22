package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.UserAccess;
import com.wyer.server.entity.Cart;
import com.wyer.server.service.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/11/26 15:04
 **/
@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    CartServiceImpl cartService;

    /**
     * 添加商品至购物车
     * @param cart
     * @return Result
     */
    @UserAccess
    @PostMapping(value = "/add")
    public Result add(@RequestBody Cart cart) {
        return Result.success(cartService.add(cart));
    }

    /**
     * 根据用户id获取购物车信息
     * @param uid
     * @return List<CartTableRow>
     */
    @UserAccess
    @PostMapping(value = "/get")
    public Result get(@RequestBody Integer uid) {
        return Result.success(cartService.get(uid));
    }

    /**
     * 从购物车删除一件商品
     * @param cart
     * @return List<CartTableRow>
     */
    @UserAccess
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody Cart cart) {
        return Result.success(cartService.delete(cart));
    }

    /**
     * 从购物车删除复数商品
     * @param carts
     * @return List<CartTableRow>
     */
    @UserAccess
    @PostMapping(value = "/batch/delete")
    public Result batchDelete(@RequestBody List<Cart> carts) {
        return Result.success(cartService.batchDelete(carts));
    }

    /**
     * 保存购物车的某件商品信息
     * @param cart
     * @return List<CartTableRow>
     */
    @UserAccess
    @PostMapping(value = "/save")
    public Result updateNumber(@RequestBody Cart cart) {
        return Result.success(cartService.updateNumber(cart));
    }

    /**
     * 保存购物车的所有商品信息
     * @param carts
     * @return List<CartTableRow>
     */
    @UserAccess
    @PostMapping(value = "batch/save")
    public Result batchUpdateNumber(@RequestBody List<Cart> carts) {
        return Result.success(cartService.batchUpdateNumber(carts));
    }
}
