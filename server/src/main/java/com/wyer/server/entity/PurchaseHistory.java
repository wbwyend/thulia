package com.wyer.server.entity;

import lombok.Data;

/**
 * Function: 购买记录实体类
 * Writer: wyer
 * Date: 2023/12/05 20:34
 **/
@Data
public class PurchaseHistory {

    private Integer id;
    private Integer uid;
    private Integer sid;
    private Integer gid;
    private Integer number;
    private Double total;
    private Integer oid;
}
