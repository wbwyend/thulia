package com.wyer.server.model.vo;

import lombok.Data;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/12/03 19:28
 **/
@Data
public class BrowseHistoryDetailsForUser {

    public Integer id;
    public Integer uid;
    public Integer gid;
    public String shopname;
    public String name;
    public String time;
}
