package com.wyer.server.service.impl;

import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.BigDataMapper;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.model.entity.Goods;
import com.wyer.server.model.entity.PurchaseHistory;
import com.wyer.server.model.entity.SalerOperation;
import com.wyer.server.model.entity.ShopOperation;
import com.wyer.server.service.GoodsService;
import com.wyer.server.utils.ContextMapUtils;
import com.wyer.server.utils.IPv4Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function: 商品服务类
 * Writer: wyer
 * Date: 2023/10/19 12:07
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    private BigDataMapper bigDataMapper;

    /**
     * 添加新商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    @Transactional
    public List<Goods> add(Goods goods) {
        List<Goods> goodsList;
        goodsMapper.add(goods);
        goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
        bigDataMapper.saveSalerOperation(new SalerOperation(ContextMapUtils.getContextId(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "add",
                "goods"));
        return goodsList;
    }

    /**
     * 使用sid和状态获得商品
     * @param sid
     * @param state
     * @return List<Goods>
     */
    @Override
    public List<Goods> getShopGoodsBySidAndState(Integer sid, String state) {
        return goodsMapper.selectShopGoodsBySidAndState(sid, state);
    }

    /**
     * 下架商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    @Transactional
    public List<Goods> deleteShopGoods(Goods goods) {
        List<Goods> goodsList;
        goodsMapper.deleteShopGoods(goods.getGid());
        goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
        bigDataMapper.saveSalerOperation(new SalerOperation(goods.getSaler(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "del",
                "goods"));
        return goodsList;
    }

    /**
     * 批量下架商品
     * @param goodsList
     * @return List<Goods>
     */
    @Transactional
    public List<Goods> batchDeleteShopGoods(List<Goods> goodsList) {
        List<Integer> list = new ArrayList<>();
        for (Goods goods : goodsList) {
            list.add(goods.getGid());
        }
        goodsMapper.batchDeleteShopGoods(list);
        goodsList = goodsMapper.selectShopGoodsBySid(goodsList.get(0).getSid());
        bigDataMapper.saveSalerOperation(new SalerOperation(goodsList.get(0).getSaler(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "upd",
                "goods"));
        return goodsList;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return List<Goods>
     */
    @Override
    @Transactional
    public List<Goods> updateShopGoods(Goods goods) {
        List<Goods> goodsList;
        goodsMapper.update(goods);
        if (goods.getOnsale().equals("true")) {
            goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
        } else {
            goodsList = goodsMapper.selectShopGoodsBySidAndState(goods.getSid(), "false");
        }
        bigDataMapper.saveShopOperation(new ShopOperation(goods.getSid(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "upd",
                "goods"));
        return goodsList;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return
     */
    @Transactional
    public List<Goods> updateSalerGoods(Goods goods) {
        List<Goods> goodsList;
        goodsMapper.update(goods);
        if (goods.getOnsale().equals("true")) {
            goodsList = goodsMapper.selectSalerGoodsBySalerIdAndState(goods.getSaler(), "true");
        } else {
            goodsList = goodsMapper.selectShopGoodsBySidAndState(goods.getSaler(), "false");
        }
        bigDataMapper.saveSalerOperation(new SalerOperation(goods.getSaler(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "del",
                "goods"));
        return goodsList;
    }

    /**
     * 重新上架已下架商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    @Transactional
    public List<Goods> listShopGoods(Goods goods) {
        List<Goods> goodsList;
        goodsMapper.listShopGoods(goods.getGid());
        goodsList = goodsMapper.selectShopGoodsBySidAndState(goods.getSid(), "false");
        bigDataMapper.saveSalerOperation(new SalerOperation(goods.getSaler(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "lst",
                "goods"));
        return goodsList;
    }

    /**
     * 批量上架已下架商品
     * @param goodsList
     * @return List<Goods>
     */
    @Transactional
    public List<Goods> batchListShopGoods(List<Goods> goodsList) {
        List<Integer> list = new ArrayList<>();
        for (Goods goods : goodsList) {
            list.add(goods.getGid());
        }
        int salerId = goodsList.get(0).getSaler();
        goodsMapper.batchListShopGoods(list);
        goodsList = goodsMapper.selectShopGoodsBySidAndState(goodsList.get(0).getSid(), "false");
        bigDataMapper.saveSalerOperation(new SalerOperation(salerId,
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "lst",
                "goods"));
        return goodsList;
    }

    /**
     * 使用gid获得商品
     * @param gid
     * @return Goods
     */
    @Override
    public Goods selectByGid(Integer gid) {
        return goodsMapper.selectByGid(gid);
    }

    /**
     * 检查购买数量是否超过库存
     * @param list
     * @return String
     */
    @Override
    public String examineSurplus(List<PurchaseHistory> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (PurchaseHistory purchaseHistory : list) {
            map.put(purchaseHistory.getGid(), purchaseHistory.getNumber());
        }
        List<Goods> goodsList = goodsMapper.selectGoodsNameByGidList(new ArrayList<>(map.keySet()));
        for (Goods goods : goodsList) {
            if (map.get(goods.getGid()) > goods.getSurplus()) {
                return "false";
            }
        }
        return "true";
    }

    /**
     * 首页商品展示
     * @return List<Goods>
     */
    @Override
    public List<Goods> recommend() {
        return goodsMapper.selectAll();
    }

    /**
     * 模糊搜索商品并排序
     * @param text
     * @return List<Goods>
     */
    @Override
    public List<Goods> search(String text) {
        text = text.substring(0, Math.min(text.length(), 8));
        List<Goods> goodsList = goodsMapper.selectAll();
        Map<Integer, List<Goods>> maps = new HashMap<>();
        for (Goods goods : goodsList) {
            String name = goods.getName();
            int value = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 1; i + j <= text.length(); j++) {
                    if (name.contains(text.substring(i, i + j))) {
                        value++;
                    }
                }
            }
            if (value != 0) {
                if (maps.containsKey(value)) {
                    maps.get(value).add(goods);
                } else {
                    List<Goods> temp = new ArrayList<>();
                    temp.add(goods);
                    maps.put(value, temp);
                }
            }
        }
        goodsList.clear();
        List<Integer> values = new ArrayList<>(maps.keySet());
        for (int i = values.size() - 1; i >= 0; i--) {
            goodsList.addAll(maps.get(values.get(i)));
        }
        return goodsList;
    }

    /**
     * 查询销售人员负责的商品
     * @param id
     * @param state
     * @return
     */
    @Override
    public List<Goods> getSalerGoodsBySidAndState(Integer id, String state) {
        return goodsMapper.selectSalerGoodsBySalerIdAndState(id, state);
    }
}
