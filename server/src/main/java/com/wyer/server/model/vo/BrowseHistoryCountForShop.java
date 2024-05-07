package com.wyer.server.model.vo;

import com.wyer.server.model.entity.BrowseHistory;
import lombok.Data;

/**
 * Function: 浏览记录统计结果
 * Writer: wyer
 * Date: 2023/11/02 11:31
 **/
@Data
public class BrowseHistoryCountForShop {

    private Integer gid;
    private String name;
    private Integer count;

    public BrowseHistoryCountForShop(BrowseHistory bh) {
        setGid(bh.getGid());
        setCount(1);
    }
    public BrowseHistoryCountForShop() {
    }


}
