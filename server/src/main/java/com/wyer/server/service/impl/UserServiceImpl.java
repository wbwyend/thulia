package com.wyer.server.service.impl;

import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.UserMapper;
import com.wyer.server.model.entity.User;
import com.wyer.server.model.vo.Password;
import com.wyer.server.service.UserService;
import com.wyer.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Function: 用户服务类
 * Writer: wyer
 * Date: 2023/09/17 14:20
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户登录服务
     * @param user
     * @return User
     */
    @Override
    public User login(User user) {
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser == null) {
            throw new ServiceException("账号或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("账号或密码错误");
        }
        if (dbUser.getUsername().startsWith("deleted_user")) {
            throw new ServiceException("账号或密码错误");
        }
        // 生成token
        dbUser.setToken(TokenUtils.createToken(dbUser.getUid().toString(), dbUser.getPassword()));
        //去除密码等敏感信息
        dbUser.setPassword("");
        return dbUser;
    }

    /**
     * 用户注册服务
     * @param user
     */
    @Override
    public void register(User user) {
        try {
            userMapper.insertUser(user);
        } catch (DuplicateKeyException e) {
            throw new ServiceException("账号重名");
        }
    }

    @Override
    public User modify(User user) {
        try {
            userMapper.updateUser(user);
        } catch (DuplicateKeyException e) {
            throw new ServiceException("账号重名");
        }
        User dbUser = userMapper.selectByID(user.getUid());
        dbUser.setToken(TokenUtils.createToken(dbUser.getUid().toString(), dbUser.getPassword()));
        dbUser.setPassword("");
        return dbUser;
    }

    /**
     * 更改用户密码
     * @param password
     * @return User
     */
    @Override
    public User modify(Password password) {
        User dbUser = userMapper.selectByID(password.getId());
        if (!dbUser.getPassword().equals(password.getOldpassword())) {
            throw new ServiceException("原密码错误");
        }
        userMapper.updatePassword(password.getId(), password.getNewpassword());
        dbUser.setToken(TokenUtils.createToken(dbUser.getUid().toString(), password.getNewpassword()));
        dbUser.setPassword("");
        return dbUser;
    }

    @Override
    public Object closeUser(Integer uid) {
        userMapper.updateUserNameToDeleted(uid, "deleted_user" + UUID.randomUUID());
        return null;
    }
}
