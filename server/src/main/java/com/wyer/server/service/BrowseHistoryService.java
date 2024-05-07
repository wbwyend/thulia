package com.wyer.server.service;

import com.wyer.server.model.entity.BrowseHistory;
import com.wyer.server.model.vo.BrowseHistoryCountForShop;
import com.wyer.server.model.vo.BrowseHistoryDetailsForShop;

import java.util.List;

/**
 * Function: 浏览记录服务类
 * Writer: wyer
 * Date: 2023/10/23 15:47
 **/
public interface BrowseHistoryService {

    void add(BrowseHistory browseHistory);

    List<BrowseHistoryCountForShop> getShop(Integer sid);

    List<BrowseHistoryCountForShop> getSaler(Integer sid, Integer salerId);

    List<BrowseHistoryDetailsForShop> getShopDetails(Integer gid);
}
