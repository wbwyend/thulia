package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Function: 购物车商品类
 * Writer: wyer
 * Date: 2023/11/26 14:50
 **/
@Data
public class Cart {
    private Integer uid;
    private Integer gid;
    private Integer number;
}
