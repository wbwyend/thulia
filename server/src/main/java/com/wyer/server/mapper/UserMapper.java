package com.wyer.server.mapper;

import com.wyer.server.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function:
 * Writer: wyer
 * Date: 2023/09/17 14:44
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into `user` (username, password, name, phone, email, address, avatar)" +
            " values (#{username}, #{password}, #{name}, #{phone}, #{email}, #{address}, #{avatar})")
    void insertUser(User user);

    @Update("update `user` set password = #{password}, name = #{name}, phone = #{phone}, email = #{email}," +
            "address = #{address}, avatar = #{avatar} where id = #{id}")
    void updateUser(User user);

    @Delete("delete from `user` where id = #{id}")
    void deleteUser(Integer id);

    List<User> selectAll();

    @Select("select * from `user` where username = #{username} and name = #{name}")
    List<User> selectByMore(@Param("username") String username, @Param("name") String name);

    @Select("select * from `user` where name like concat('%', #{name}, '%')")
    List<User> selectByFuzzySearch(@Param("name") String name);

    void deleteUsers(@Param("ids") List<Integer> ids);

    List<User> selectByID(@Param("id") Integer id);

}
