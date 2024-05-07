package com.wyer.server.model.entity;

import lombok.Data;

import java.math.BigDecimal;

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
    private BigDecimal total;
    private Integer oid;
}
