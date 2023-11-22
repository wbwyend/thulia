package com.wyer.server.service;

import com.wyer.server.entity.Goods;
import com.wyer.server.entity.PurchaseHistory;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class GoodsServiceImpl implements  GoodsService{

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 添加新商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    public List<Goods> add(Goods goods) {
        List<Goods> goodsList;
        /*try {
            goods.setOnsale("true");
            goodsMapper.add(goods);
            goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }*/
        goodsMapper.add(goods);
        goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
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
        try {
            return goodsMapper.selectShopGoodsBySidAndState(sid, state);
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
    }

    /**
     * 下架商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    public List<Goods> deleteShopGoods(Goods goods) {
        List<Goods> goodsList;
        try {
            goodsMapper.deleteShopGoods(goods.getGid());
            goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return goodsList;
    }

    /**
     * 批量下架商品
     * @param goodsList
     * @return List<Goods>
     */
    public List<Goods> batchDeleteShopGoods(List<Goods> goodsList) {
        try {
            List<Integer> list = new ArrayList<>();
            for (Goods goods : goodsList) {
                list.add(goods.getGid());
            }
            goodsMapper.batchDeleteShopGoods(list);
            goodsList = goodsMapper.selectShopGoodsBySid(goodsList.get(0).getSid());
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return goodsList;
    }

    /**
     * 更新商品信息
     * @param goods
     * @return List<Goods>
     */
    @Override
    public List<Goods> updateShopGoods(Goods goods) {
        List<Goods> goodsList;
        try {
            goodsMapper.update(goods);
            if (goods.getOnsale().equals("true")) {
                goodsList = goodsMapper.selectShopGoodsBySid(goods.getSid());
            } else {
                goodsList = goodsMapper.selectShopGoodsBySidAndState(goods.getSid(), "false");
            }
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return goodsList;
    }

    /**
     * 重新上架已下架商品
     * @param goods
     * @return List<Goods>
     */
    @Override
    public List<Goods> listShopGoods(Goods goods) {
        List<Goods> goodsList;
        try {
            goodsMapper.listShopGoods(goods.getGid());
            goodsList = goodsMapper.selectShopGoodsBySidAndState(goods.getSid(), "false");
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return goodsList;
    }

    /**
     * 批量上架已下架商品
     * @param goodsList
     * @return List<Goods>
     */
    public List<Goods> batchListShopGoods(List<Goods> goodsList) {
        try {
            List<Integer> list = new ArrayList<>();
            for (Goods goods : goodsList) {
                list.add(goods.getGid());
            }
            goodsMapper.batchListShopGoods(list);
            goodsList = goodsMapper.selectShopGoodsBySidAndState(goodsList.get(0).getSid(), "false");
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return goodsList;
    }

    /**
     * 使用gid获得商品
     * @param gid
     * @return Goods
     */
    @Override
    public Goods selectByGid(Integer gid) {
        try {
            return goodsMapper.selectByGid(gid);
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
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
}
