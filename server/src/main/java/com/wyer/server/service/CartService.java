package com.wyer.server.service;

import com.wyer.server.model.entity.Cart;
import com.wyer.server.model.vo.CartTableRow;

import java.util.List;

/**
 * Function: 购物车服务接口
 * Writer: wyer
 * Date: 2023/11/26 15:07
 **/
public interface CartService {
    Object add(Cart cart);

    List<CartTableRow> get(Integer uid);

    List<CartTableRow> delete(Cart cart);

    List<CartTableRow> batchDelete(List<Cart> carts);

    List<CartTableRow> updateNumber(Cart cart);

    List<CartTableRow> batchUpdateNumber(List<Cart> carts);
}
