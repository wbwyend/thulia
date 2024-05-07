package com.wyer.server.model.vo;

import lombok.Data;

/**
 * Function: 商家查看的购买记录
 * Writer: wyer
 * Date: 2023/12/06 15:49
 **/
@Data
public class PurchaseHistoryForShopView {

    private Integer number;
    private Double total;
    private String picture;
    private String name;
    private String username;
    private String time;
}
