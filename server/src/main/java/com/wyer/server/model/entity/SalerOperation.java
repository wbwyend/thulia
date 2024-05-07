package com.wyer.server.model.entity;

import lombok.Data;

/**
 * Name: SalerLoginData
 * Writer: wyer
 **/
@Data
public class SalerOperation {
    private int id;
    private int salerId;
    private long time;
    private String ip;
    /**
     * li: 登录
     * lo: 登出
     * add: 添加
     * del: 删除
     * upd: 更新
     * lst: 上架
     */
    private String type;
    private String object;

    public SalerOperation() {}
    public SalerOperation(int salerId, long time, String ip, String type, String object) {
        this.salerId = salerId;
        this.time = time;
        this.ip = ip;
        this.type = type;
        this.object = object;
    }
}
