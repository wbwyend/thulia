package com.wyer.server.common;

import lombok.Data;

/**
 * Function: 用户支付的购买记录
 * Writer: wyer
 * Date: 2023/12/05 22:33
 **/
@Data
public class PurchaseHistoryForUserPay {

    private Integer number;
    private Double total;
    private Integer oid;
    private String picture;
    private String name;
}
