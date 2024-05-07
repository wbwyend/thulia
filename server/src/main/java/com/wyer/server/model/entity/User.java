package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Function: 用户类
 * Writer: wyer
 * Date: 2023/09/17 14:03
 **/
@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;

    private String token;

}
