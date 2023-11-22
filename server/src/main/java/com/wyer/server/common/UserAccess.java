package com.wyer.server.common;

import java.lang.annotation.*;

/**
 * Function: 拦截器User访问控制注解
 * Writer: wyer
 * Date: 2023/09/30 11:45
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserAccess {
}
