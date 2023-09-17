package com.wyer.server.service;

import com.wyer.server.entity.User;

import java.util.List;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/17 14:31
 **/
public interface UserService {
    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);

    List<User> select();

    List<User> select(Integer id);

    List<User> select(String username, String name);

    List<User> select(String name);

    void deleteUsers(List<Integer> ids);

}
