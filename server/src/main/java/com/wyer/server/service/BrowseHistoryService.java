package com.wyer.server.service;

import com.wyer.server.common.BrowseHistoryCountForShop;
import com.wyer.server.common.BrowseHistoryDetailsForShop;
import com.wyer.server.entity.BrowseHistory;

import java.util.List;

/**
 * Function: 浏览记录服务类
 * Writer: wyer
 * Date: 2023/10/23 15:47
 **/
public interface BrowseHistoryService {

    void add(BrowseHistory browseHistory);

    List<BrowseHistoryCountForShop> getShop(Integer sid);

    List<BrowseHistoryDetailsForShop> getShopDetails(Integer gid);
}
