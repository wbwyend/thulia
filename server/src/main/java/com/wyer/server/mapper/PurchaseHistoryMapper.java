package com.wyer.server.mapper;

import com.wyer.server.model.entity.PurchaseHistory;
import com.wyer.server.model.vo.PurchaseHistoryForShopView;
import com.wyer.server.model.vo.PurchaseHistoryForUserPay;
import com.wyer.server.model.vo.PurchaseHistoryForUserView;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Function: 购买记录表表接口
 * Writer: wyer
 * Date: 2023/12/05 20:40
 **/
@Mapper
public interface PurchaseHistoryMapper {
    @Insert("insert into purchase_history (uid, sid, gid, number, total, oid) values (#{uid}, #{sid}, #{gid}," +
            "#{number}, #{total}, #{oid})")
    void insertPurchaseHistory(PurchaseHistory purchaseHistory);

    void batchInsertPurchaseHistory(List<PurchaseHistory> list);

    @Select("SELECT purchase_history.number, goods.`name`, purchase_history.oid, goods.picture," +
            " purchase_history.total FROM purchase_history, goods WHERE purchase_history.gid = goods.gid and " +
            "purchase_history.oid = #{oid}")
    List<PurchaseHistoryForUserPay> selectPurchaseHistoryForUserPayByOid(@Param("oid") Integer oid);

    @Update("update purchase_history set time = #{time} where oid = #{oid}")
    void updateTimeByOid(@Param("oid") Integer oid, @Param("time") String time);

    @Select("SELECT purchase_history.number, goods.`name`, purchase_history.time, goods.picture," +
            " purchase_history.total, shop.shopname FROM purchase_history, goods, shop, `order` WHERE" +
            " purchase_history.gid = goods.gid AND goods.sid = shop.sid AND purchase_history.uid = #{uid} AND" +
            " `order`.`status` = '已完成' AND `order`.oid = purchase_history.oid ORDER BY purchase_history.time DESC")
    List<PurchaseHistoryForUserView> selectPurchaseHistoryByUid(@Param("uid") Integer uid);

    @Select("SELECT purchase_history.number, goods.`name`, purchase_history.time, goods.picture," +
            " purchase_history.total, `user`.username FROM purchase_history, goods, `user`," +
            " `order` WHERE purchase_history.gid = goods.gid AND purchase_history.uid = `user`.uid" +
            " AND `order`.`status` = '已完成' AND `order`.oid = purchase_history.oid AND" +
            " purchase_history.sid = #{sid} ORDER BY purchase_history.time DESC")
    List<PurchaseHistoryForShopView> selectPurchaseHistoryBySid(@Param("sid")Integer sid);

    @Select("SELECT purchase_history.number, goods.`name`, purchase_history.time, goods.picture," +
            " purchase_history.total, `user`.username FROM purchase_history, goods, `user`," +
            " `order` WHERE purchase_history.gid = goods.gid AND purchase_history.uid = `user`.uid" +
            " AND `order`.`status` = '已完成' AND `order`.oid = purchase_history.oid AND" +
            " goods.saler = #{saler} ORDER BY purchase_history.time DESC")
    List<PurchaseHistoryForShopView> selectPurchaseHistoryBySalerId(@Param("saler")Integer saler);

    @Select("select * from purchase_history where oid = #{oid}")
    List<PurchaseHistory> selectPurchaseHistoryByOid(@Param("oid") Integer oid);

}
