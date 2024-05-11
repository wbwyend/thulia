package com.wyer.server.service.impl;

import com.wyer.server.mapper.BigDataMapper;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.model.entity.Goods;
import com.wyer.server.model.entity.LoginData;
import com.wyer.server.model.entity.SalerOperation;
import com.wyer.server.model.entity.ShopOperation;
import com.wyer.server.model.vo.SalesVolumeByDate;
import com.wyer.server.model.vo.UserCFObject;
import com.wyer.server.model.vo.UserPortrait;
import com.wyer.server.service.BigDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Name: BigDataServiceImpl
 * Writer: wyer
 **/
@Service
public class BigDataServiceImpl implements BigDataService {
    @Autowired
    private BigDataMapper bigDataMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    public void saveLoginData(LoginData loginData) {
        bigDataMapper.saveLoginData(loginData);
    }

    public void saveShopOperation(ShopOperation shopOperation) {
        bigDataMapper.saveShopOperation(shopOperation);
    }

    public void saveSalerOperation(SalerOperation salerOperation) {
        bigDataMapper.saveSalerOperation(salerOperation);
    }

    public UserPortrait getUserPortrait(int userId) {
        return bigDataMapper.getUserPortrait(userId);
    }

    public List<Goods> getRecommendGoods(int uid) {

        final int userCount = bigDataMapper.getCountUser();
        final int goodsCount = bigDataMapper.getCountGoods();
        int userId = uid != 0 ? uid : new Random().nextInt(userCount);
        final double browseRatio = 0.3;

        // 1.建立矩阵
        List<UserCFObject> browseHistoryData = bigDataMapper.getBrowseHistoryData();
        Map<Integer, Map<Integer, Integer>> browseHistoryDataMap = new HashMap<>();
        for (UserCFObject u : browseHistoryData) {
            if (!browseHistoryDataMap.containsKey(u.getUid())) {
                Map<Integer, Integer> sonMap = new HashMap<>();
                sonMap.put(u.getGid(), u.getScore());
                browseHistoryDataMap.put(u.getUid(), sonMap);
            } else {
                Map<Integer, Integer> sonMap = browseHistoryDataMap.get(u.getUid());
                sonMap.put(u.getGid(), u.getScore());
            }
        }

        List<UserCFObject> purchaseHistoryData = bigDataMapper.getPurChaseHistoryData();
        Map<Integer, Map<Integer, Integer>> purchaseHistoryDataMap = new HashMap<>();
        for (UserCFObject u : purchaseHistoryData) {
            if (!purchaseHistoryDataMap.containsKey(u.getUid())) {
                Map<Integer, Integer> sonMap = new HashMap<>();
                sonMap.put(u.getGid(), u.getScore());
                purchaseHistoryDataMap.put(u.getUid(), sonMap);
            } else {
                Map<Integer, Integer> sonMap = purchaseHistoryDataMap.get(u.getUid());
                sonMap.put(u.getGid(), u.getScore());
            }
        }



        // 2.余弦相似度计算
        double param1 = 0, param2 = 0.0;
        for (int i = 1; i <= goodsCount; i++) {
            double temp1 = browseHistoryDataMap.get(userId) != null ? browseHistoryDataMap.get(userId).getOrDefault(i, 0) : 0.0;
            double temp2 = purchaseHistoryDataMap.get(userId) != null ? purchaseHistoryDataMap.get(userId).getOrDefault(i, 0) : 0.0;
            param1 += temp1 * temp1;
            param2 += temp2 * temp2;
        }
        param1 = Math.sqrt(param1);
        param2 = Math.sqrt(param2);
        PriorityQueue<Map.Entry<Integer, Double>> queue = new PriorityQueue<>(10, (o1, o2) -> o1.getValue() > o2.getValue() ? 1 : -1);
        int size = 10;
        for (int j = 1; j <= userCount; j++) {
            double param5 = 0;
            double param6 = 0;
            double param3 = 0;
            double param4 = 0;
            for (int i = 1; i <= goodsCount; i++) {
                Map<Integer, Integer> tempMap;
                double temp1 = browseHistoryDataMap.get(userId) != null ? browseHistoryDataMap.get(userId).getOrDefault(i, 0) : 0.0;
                double temp2 = browseHistoryDataMap.get(j) != null ? browseHistoryDataMap.get(j).getOrDefault(i, 0) : 0.0;
                double temp3 = purchaseHistoryDataMap.get(userId) != null ? purchaseHistoryDataMap.get(userId).getOrDefault(i, 0) : 0.0;
                double temp4 = purchaseHistoryDataMap.get(j) != null ? purchaseHistoryDataMap.get(j).getOrDefault(i, 0) : 0.0;
                param5 += temp1 * temp2;
                param6 += temp3 * temp4;
                param3 += temp2 * temp2;
                param4 += temp4 * temp4;
            }
            param3 = Math.sqrt(param3);
            param4 = Math.sqrt(param4);

            double similarity = 0;
            if (param1 != 0 && param3 != 0) similarity += param5 / param1 / param3 * browseRatio;
            if (param2 != 0 && param4 != 0) similarity += param6 / param2 / param4 * (1 - browseRatio);

            // 3.获取相似度最大的用户id
            Map.Entry<Integer, Double> entry = new AbstractMap.SimpleEntry<>(j, similarity);
            if (size-- >= 0) {
                queue.add(entry);
            } else {
                if (!queue.isEmpty() && entry.getValue() > queue.peek().getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }

        List<Integer> uidList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            if (!queue.isEmpty()) {
                Map.Entry<Integer, Double> entry = queue.poll();
                uidList.add(entry.getKey());
            }

        }

        // 4. 推荐商品
        List<Integer> gidList = bigDataMapper.getRecommendGoods(uidList, new Random().nextInt(10));

        return goodsMapper.selectGoodsNameByGidList(gidList);
    }


    public List<SalesVolumeByDate> getSalesVolumeByDate(int gid) {
        return bigDataMapper.getSalesVolumeByDate(gid);
    }
}
