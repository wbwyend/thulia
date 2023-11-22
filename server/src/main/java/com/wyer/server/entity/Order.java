package com.wyer.server.entity;

import lombok.Data;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/12/05 16:51
 **/
@Data
public class Order {
    private Integer oid;
    private Integer uid;
    private Double total;
    private String create_time;
    private String end_time;
    private String ordername;
    private String status;
    private String alipay_trade_no;
}
