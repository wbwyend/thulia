package com.wyer.server.service;

import com.wyer.server.common.Password;
import com.wyer.server.entity.User;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.UserMapper;
import com.wyer.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
            // 抛出自定义异常
            throw new ServiceException("账号或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("账号或密码错误");
        }
        if (dbUser.getUsername().equals("deleted_user")) {
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
        userMapper.insertUser(user);
    }

    @Override
    public User modify(User user) {
        try {
            userMapper.updateUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new ServiceException("账号重名");
            } else {
                throw new ServiceException("系统错误");
            }
        }
        User dbUser = userMapper.selectByID(user.getUid());
        dbUser.setToken(TokenUtils.createToken(dbUser.getUid().toString(), dbUser.getPassword()));
        dbUser.setPassword("");
        return dbUser;
    }

    /**
     * 更改商家密码
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
        try {
            userMapper.updateUserNameToDeleted(uid);
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
        return null;
    }
}
