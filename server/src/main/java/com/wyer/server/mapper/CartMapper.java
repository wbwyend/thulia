package com.wyer.server.mapper;

import com.wyer.server.common.CartTableRow;
import com.wyer.server.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function: 购物车表接口
 * Writer: wyer
 * Date: 2023/11/26 14:52
 **/
@Mapper
public interface CartMapper {

    @Insert("insert into cart (uid, gid, number) values (#{uid}, #{gid}, #{number})")
    void add(Cart cart);

    @Select("SELECT shop.sid, goods.picture, cart.gid, cart.uid, goods.name, goods.price, cart.number, shop.shopname FROM goods, cart, shop WHERE cart.gid = goods.gid AND goods.sid = shop.sid And cart.uid = #{uid}")
    List<CartTableRow> selectCartTableRows(@Param("uid") Integer uid);

    @Delete("delete from cart where uid = #{uid} and gid = #{gid}")
    void delete(@Param("uid") Integer uid, @Param("gid") Integer gid);

    void batchDelete(@Param("uid") Integer uid, @Param("gids") List<Integer> gids);

    @Update("update cart set number = #{number} where uid = #{uid} and gid = #{gid}")
    void updateNumber(Cart cart);

    void batchUpdateNumber(@Param("carts") List<Cart> carts);
}
