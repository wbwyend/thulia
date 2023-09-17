package com.wyer.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/17 14:03
 **/
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;

}
