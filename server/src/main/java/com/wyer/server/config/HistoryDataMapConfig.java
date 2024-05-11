package com.wyer.server.config;

import com.wyer.server.mapper.BigDataMapper;
import com.wyer.server.model.vo.UserCFObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: HistoryMapConfig
 * Writer: wyer
 **/
@Configuration
public class HistoryDataMapConfig {

    @Autowired
    private BigDataMapper bigDataMapper;

    @Bean(value = "BrowseHistoryDataMap")
    public Map<Integer, Map<Integer, Integer>> getBrowseHistoryMap() {
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
        return browseHistoryDataMap;
    }

    @Bean(value = "PurchaseHistoryDataMap")
    public Map<Integer, Map<Integer, Integer>> getPurchaseHistoryMap() {
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
        return purchaseHistoryDataMap;
    }
}
