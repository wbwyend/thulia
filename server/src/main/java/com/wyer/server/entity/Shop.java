package com.wyer.server.entity;

import lombok.Data;

/**
 * Function: 商家类
 * Writer: wyer
 * Date: 2023/09/29 18:01
 **/
@Data
public class Shop {
    private Integer sid;
    private String username;
    private String password;
    private String shopname;
    private String phone;
    private String email;
    private String address;
    private String avatar;

    private String token;
}
