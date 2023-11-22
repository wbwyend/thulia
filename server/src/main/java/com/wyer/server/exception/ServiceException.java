package com.wyer.server.exception;

import lombok.Getter;

/**
 * Function: 自定义服务异常
 * Writer: wyer
 * Date: 2023/09/29 14:42
 **/
@Getter
public class ServiceException extends RuntimeException{

    private final String code;
    public ServiceException(String msg) {
        super(msg);
        this.code = "500"; // 默认状态码
    }
    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
