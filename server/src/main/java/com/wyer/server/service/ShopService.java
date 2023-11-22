package com.wyer.server.service;

import com.wyer.server.common.Password;
import com.wyer.server.entity.Shop;

/**
 * Function: 商家服务类
 * Writer: wyer
 * Date: 2023/09/29 18:18
 **/
public interface ShopService {

    Shop login(Shop shop);

    void register(Shop shop);

    Shop modify(Shop shop);

    Shop modify(Password password);

    Shop getDetailsPageShopInformation(Integer sid);
}
