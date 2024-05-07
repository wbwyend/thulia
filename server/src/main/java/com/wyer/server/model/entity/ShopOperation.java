package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Name: ShopLoginData
 * Writer: wyer
 **/
@Data
public class ShopOperation {
    private int id;
    private int sid;
    private long time;
    private String ip;
    private String type;
    private String object;

    public ShopOperation() {}
    public ShopOperation(int sid, long time, String ip, String type, String object) {
        this.sid = sid;
        this.time = time;
        this.ip = ip;
        this.type = type;
        this.object = object;
    }
}
