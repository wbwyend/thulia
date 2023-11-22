package com.wyer.server.mapper;

import com.wyer.server.entity.Shop;
import com.wyer.server.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function: 商家表接口
 * Writer: wyer
 * Date: 2023/09/29 18:21
 **/

@Mapper
public interface ShopMapper {

    @Select("select * from shop where username = #{username}")
    Shop selectByUsername(@Param("username") String username);

    @Insert("insert into shop (username, password, shopname, phone, email, address, avatar)" +
            " values (#{username}, #{password}, #{shopname}, #{phone}, #{email}, #{address}, #{avatar})")
    void insertShop(Shop shop);

    @Select("select * from shop where sid = #{sid}")
    Shop selectByID(@Param("sid") Integer sid);

    @Update("update shop set username = #{username}, shopname = #{shopname}, phone = #{phone}, email = #{email}," +
            "address = #{address}, avatar = #{avatar} where sid = #{sid}")
    void updateShop(Shop shop);

    @Update("update shop set password = #{password} where sid = #{sid}")
    void updatePassword(@Param("sid") Integer sid, @Param("password") String password);

    List<Shop> selectShopNameBySidList(@Param("ids") List<Integer> ids);

}
