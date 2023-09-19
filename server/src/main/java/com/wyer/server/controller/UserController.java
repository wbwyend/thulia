package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.entity.User;
import com.wyer.server.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/17 14:21
 **/
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    /**
     * 新增用户信息
     */
    @PostMapping(value = "/add")
    public Result add(@RequestBody User user) {
        try {
            userServiceImpl.insertUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success();
    }

    /**
     * 修改用户信息
     */
    @PutMapping(value = "/update")
    public Result update(@RequestBody User user) {
        try {
            userServiceImpl.updateUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("修改数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success();
    }

    /**
     * 删除单个用户信息
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        try {
            userServiceImpl.deleteUser(id);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("删除数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success();
    }

    /**
     * 批量删除用户信息
     */
    @DeleteMapping(value = "/delete/batch")
    public Result delete(@RequestBody List<Integer> ids) {
        try {
            userServiceImpl.deleteUsers(ids);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("删除数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @GetMapping(value = "/selectAll")
    public Result selectAll() {
        List<User> userList;
        try {
            userList = userServiceImpl.select();
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("查询数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success(userList);
    }

    /**
     * ID条件查询用户信息
     */
    @GetMapping(value = "/selectByID/{id}")
    public Result selectByID(@PathVariable Integer id) {
        List<User> userList;
        try {
            userList = userServiceImpl.select(id);
            if (userList.size() == 0) {
                return Result.error("查询数据错误");
            }
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("查询数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success(userList);
    }

    /**
     * 多条件查询用户信息
     */
    @GetMapping(value = "/selectByMore")
    public Result selectByMore(@RequestParam String username, @RequestParam String name) {
        List<User> userList;
        try {
            userList = userServiceImpl.select(username, name);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("查询数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success(userList);
    }

    /**
     * 单条件模糊查询用户信息
     */
    @GetMapping(value = "/selectByFuzzySearch")
    public Result selectByFuzzySearch(@RequestParam String name) {
        List<User> userList;
        try {
            userList = userServiceImpl.select(name);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("查询数据错误");
            } else {
                return Result.error();
            }
        }
        return Result.success(userList);
    }
}
