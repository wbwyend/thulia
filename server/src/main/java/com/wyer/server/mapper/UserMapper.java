package com.wyer.server.mapper;

import com.wyer.server.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function: 用户表接口
 * Writer: wyer
 * Date: 2023/09/17 14:44
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into user (username, password, name, phone, email, address, avatar)" +
            " values (#{username}, #{password}, #{name}, #{phone}, #{email}, #{address}, #{avatar})")
    void insertUser(User user);

    User selectByID(@Param("uid") Integer uid);

    @Select("select * from user where username = #{username}")
    User selectByUsername(@Param("username") String username);

    List<User> selectUserNameByUidList(@Param("ids") List<Integer> ids);

    @Update("update user set username = #{username}, name = #{name}, phone = #{phone}, email = #{email}," +
            "address = #{address}, avatar = #{avatar} where uid = #{uid}")
    void updateUser(User user);

    @Update("update user set password = #{password} where uid = #{uid}")
    void updatePassword(@Param("uid") Integer uid, @Param("password") String password);
}
