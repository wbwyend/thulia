package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Name: Saler
 * Writer: wyer
 **/
@Data
public class Saler {
    public int id;
    public String name;
    public String username;
    public String password;
    public int sid;
    public int active = 1;

    public String token;
}
