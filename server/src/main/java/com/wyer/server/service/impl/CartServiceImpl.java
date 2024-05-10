package com.wyer.server.service.impl;

import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.CartMapper;
import com.wyer.server.model.entity.Cart;
import com.wyer.server.model.vo.CartTableRow;
import com.wyer.server.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: 购物车服务类
 * Writer: wyer
 * Date: 2023/11/26 15:07
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartMapper cartMapper;

    /**
     * 添加商品至购物车
     * @param cart
     * @return Object
     */
    @Override
    public Object add(Cart cart) {
        try {
            cartMapper.add(cart);
        } catch (DuplicateKeyException e) {
            throw new ServiceException("已经加入购物车");
        }
        return null;
    }

    /**
     * 通过用户id获取购物车信息
     * @param uid
     * @return List<CartTableRow>
     */
    @Override
    public List<CartTableRow> get(Integer uid) {
        return cartMapper.selectCartTableRows(uid);
    }

    /**
     * 从购物车删除商品
     * @param cart
     * @return List<CartTableRow>
     */
    @Override
    public List<CartTableRow> delete(Cart cart) {
        cartMapper.delete(cart.getUid(), cart.getGid());
        return cartMapper.selectCartTableRows(cart.getUid());
    }

    /**
     * 从购物车批量删除商品
     * @param carts
     * @return List<CartTableRow>
     */
    @Override
    public List<CartTableRow> batchDelete(List<Cart> carts) {
        List<Integer> gidList = new ArrayList<>();
        for (Cart cart : carts) {
            gidList.add(cart.getGid());
        }
        cartMapper.batchDelete(carts.get(0).getUid(), gidList);
        return cartMapper.selectCartTableRows(carts.get(0).getUid());
    }

    /**
     * 更新购物车商品购买数量
     * @param cart
     * @return List<CartTableRow>
     */
    @Override
    public List<CartTableRow> updateNumber(Cart cart) {
        cartMapper.updateNumber(cart);
        return cartMapper.selectCartTableRows(cart.getUid());
    }
    /**
     * 批量更新购物车商品购买数量
     * @param carts
     * @return List<CartTableRow>
     */
    @Override
    public List<CartTableRow> batchUpdateNumber(List<Cart> carts) {
        cartMapper.batchUpdateNumber(carts);
        return cartMapper.selectCartTableRows(carts.get(0).getUid());
    }
}
