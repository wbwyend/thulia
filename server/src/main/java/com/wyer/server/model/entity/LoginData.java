package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Name: LoginData
 * Writer: wyer
 **/
@Data
public class LoginData {
    private int id;
    private int uid;
    private long time;
    private String ip;
    private String type;
}
