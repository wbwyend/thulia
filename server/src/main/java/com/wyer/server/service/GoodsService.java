package com.wyer.server.service;

import com.wyer.server.entity.Goods;
import com.wyer.server.entity.PurchaseHistory;

import java.util.List;

/**
 * Function: 商品服务类
 * Writer: wyer
 * Date: 2023/10/19 12:07
 **/
public interface GoodsService {
    List<Goods> add(Goods goods);

    List<Goods> getShopGoodsBySidAndState(Integer id, String status);

    List<Goods> deleteShopGoods(Goods goods);

    List<Goods> updateShopGoods(Goods goods);

    List<Goods> listShopGoods(Goods goods);

    Goods selectByGid(Integer gid);

    String examineSurplus(List<PurchaseHistory> list);

    List<Goods> recommend();

    List<Goods> search(String text);
}
