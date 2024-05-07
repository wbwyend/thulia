package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Function: 浏览记录实体类
 * Writer: wyer
 * Date: 2023/10/23 15:39
 **/
@Data
public class BrowseHistory {
    private Integer id;
    private Integer uid;
    private Integer sid;
    private Integer gid;
    private String time;
    private Integer seconds;
}
