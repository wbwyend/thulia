package com.wyer.server.model.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Function: 商品类
 * Writer: wyer
 * Date: 2023/10/19 11:57
 **/
@Data
public class Goods {
    private Integer gid;
    private String name;
    private BigDecimal price;
    private Integer surplus;
    private Integer sales;
    private String picture;
    private Integer sid;
    private String onsale;
    private Integer saler;
    private Integer cid;
}
