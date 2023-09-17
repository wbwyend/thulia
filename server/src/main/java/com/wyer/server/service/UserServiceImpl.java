package com.wyer.server.service;

import com.wyer.server.entity.User;
import com.wyer.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/17 14:20
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    @Override
    public List<User> select() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> select(Integer id) {
        return userMapper.selectByID(id);
    }

    @Override
    public List<User> select(String username, String name) {
        return userMapper.selectByMore(username, name);
    }

    @Override
    public List<User> select(String name) {
        return userMapper.selectByFuzzySearch(name);
    }

    @Override
    public void deleteUsers(List<Integer> ids) {
        userMapper.deleteUsers(ids);
    }
}
