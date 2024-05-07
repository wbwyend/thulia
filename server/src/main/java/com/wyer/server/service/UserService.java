package com.wyer.server.service;

import com.wyer.server.model.entity.User;
import com.wyer.server.model.vo.Password;

/**
 * Function: 用户服务类
 * Writer: wyer
 * Date: 2023/09/17 14:31
 **/
public interface UserService {

    User login(User user);

    void register(User user);

    User modify(User user);

    User modify(Password password);

    Object closeUser(Integer uid);
}
