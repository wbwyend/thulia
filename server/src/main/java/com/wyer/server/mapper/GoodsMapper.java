package com.wyer.server.mapper;

import com.wyer.server.model.entity.Goods;
import com.wyer.server.model.entity.PurchaseHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function: 商品表接口
 * Writer: wyer
 * Date: 2023/10/19 12:08
 **/
@Mapper
public interface GoodsMapper {

    @Insert("insert into goods (name, price, sales, surplus, picture, sid, saler, cid)" +
            " values (#{name}, #{price}, #{sales}, #{surplus}, #{picture}, #{sid}, #{saler}, #{cid})")
    void add(Goods goods);

    @Select("select * from goods where sid = #{sid} and onsale = #{state}")
    List<Goods> selectShopGoodsBySidAndState(@Param("sid") Integer sid, @Param("state") String state);

    @Select("select * from goods where sid = #{sid} and onsale = 'true'")
    List<Goods> selectShopGoodsBySid(@Param("sid") Integer sid);

    @Update("update goods set onsale = 'false' where gid = #{gid}")
    void deleteShopGoods(@Param("gid")Integer gid);

    void batchDeleteShopGoods(@Param("ids") List<Integer> ids);

    @Update("update goods set name = #{name}, price = #{price}, surplus = #{surplus}, picture = #{picture}, cid = #{cid} where gid = #{gid}")
    void update(Goods goods);

    @Update("update goods set onsale = 'true' where gid = #{gid}")
    void listShopGoods(@Param("gid")Integer gid);

    void batchListShopGoods(@Param("ids") List<Integer> ids);

    @Select("select * from goods where gid = #{gid}")
    Goods selectByGid(@Param("gid") Integer gid);

    List<Goods> selectGoodsNameByGidList(@Param("ids") List<Integer> ids);

    void updateSurplusByPurchase(@Param("list") List<PurchaseHistory> list);

    void updateAddSurplusByDeleteOrder(@Param("list") List<PurchaseHistory> purchaseHistoryList);

    @Select("select * from goods where onsale = 'true'")
    List<Goods> selectAll();

    @Update("UPDATE goods, purchase_history SET goods.sales = goods.sales + purchase_history.number WHERE" +
            " purchase_history.gid = goods.gid AND purchase_history.oid = #{oid}")
    void updateSalesByOid(@Param("oid") Integer oid);

    @Select("select * from goods where saler = #{id} and onsale = #{state}")
    List<Goods> selectSalerGoodsBySalerIdAndState(Integer id, String state);

    @Update("update goods set cid = #{cid} where gid = #{gid}")
    void updateCategory(Integer gid, Integer cid);
}
