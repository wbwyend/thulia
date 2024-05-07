package com.wyer.server.model.vo;

import lombok.Data;

/**
 * Function: 密码类
 * Writer: wyer
 * Date: 2023/10/01 19:15
 **/
@Data
public class Password {
    private Integer id;
    private String oldpassword;
    private String newpassword;

}
