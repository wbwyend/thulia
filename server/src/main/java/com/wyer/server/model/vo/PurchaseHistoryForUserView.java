package com.wyer.server.model.vo;

import lombok.Data;

/**
 * Function: 用户查看的购买记录
 * Writer: wyer
 * Date: 2023/12/06 15:00
 **/
@Data
public class PurchaseHistoryForUserView {

    private Integer number;
    private Double total;
    private String picture;
    private String name;
    private String shopname;
    private String time;
}
