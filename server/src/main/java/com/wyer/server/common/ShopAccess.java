package com.wyer.server.common;

import java.lang.annotation.*;

/**
 * Function: 拦截器Shop访问控制注解
 * Writer: wyer
 * Date: 2023/09/30 12:15
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShopAccess {
}
