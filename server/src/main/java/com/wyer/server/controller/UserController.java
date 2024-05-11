package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.User;
import com.wyer.server.model.vo.Password;
import com.wyer.server.service.impl.BigDataServiceImpl;
import com.wyer.server.service.impl.UserServiceImpl;
import com.wyer.server.utils.ContextMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function: 用户接口类
 * Writer: wyer
 * Date: 2023/09/17 14:21
 **/

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    private BigDataServiceImpl bigDataService;

    /**
     * 用户修改信息
     *
     * @param user
     * @return
     */
    @UserAccess
    @PostMapping(value = "/modify/information")
    public Result modifyInformation(@RequestBody User user) {
        return Result.success(userService.modify(user));
    }

    /**
     * 用户修改密码
     *
     * @param password
     * @return User
     */
    @UserAccess
    @PostMapping(value = "/modify/password")
    public Result modifyPassword(@RequestBody Password password) {
        return Result.success(userService.modify(password));
    }

    /**
     * 注销用户
     *
     * @param uid
     * @return Result
     */
    @UserAccess
    @PostMapping(value = "/delete")
    public Result closeUser(@RequestBody Integer uid) {
        return Result.success(userService.closeUser(uid));
    }


    @UserAccess
    @PostMapping(value = "/portrait")
    public Result getUserPortrait() {
        return Result.success(bigDataService.getUserPortrait(ContextMapUtils.getContextId()));
    }


}
