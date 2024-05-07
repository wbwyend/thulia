package com.wyer.server.model.vo;

import lombok.Data;

/**
 * Function: 购物车表数据行
 * Writer: wyer
 * Date: 2023/11/29 9:50
 **/
@Data
public class CartTableRow {

    public Integer uid;
    public Integer gid;
    public Integer number;
    public String name;
    public String shopname;
    public Double price;
    public String picture;
    public Integer sid;
}
