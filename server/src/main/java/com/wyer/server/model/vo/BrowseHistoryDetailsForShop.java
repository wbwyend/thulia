package com.wyer.server.model.vo;

import com.wyer.server.model.entity.BrowseHistory;
import lombok.Data;

/**
 * Function: 浏览记录详情
 * Writer: wyer
 * Date: 2023/11/22 15:41
 **/
@Data
public class BrowseHistoryDetailsForShop {
    private Integer id;
    private Integer uid;
    private String username;
    private String time;

    public BrowseHistoryDetailsForShop(BrowseHistory bh) {
        setId(bh.getId());
        setUid(bh.getUid());
        setTime(bh.getTime());
    }
}
