package com.wyer.server.mapper;

import com.wyer.server.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Function: 订单表接口
 * Writer: wyer
 * Date: 2023/12/05 19:48
 **/
@Mapper
public interface OrderMapper {
    @Select("select * from `order` where uid = #{uid} order by create_time desc")
    List<Order> selectOrdersByUid(@Param("uid") Integer uid);
    @Insert("insert into `order` (uid, ordername, create_time, status, total) values (#{uid}, #{ordername}," +
            " #{create_time}, #{status}, #{total})")
    void insertOrder(Order order);

    @Select("select oid from `order` where create_time = #{createTime} and uid = #{uid}")
    Integer selectOrdersByUidAndCreateTime(@Param("createTime") String createTime, @Param("uid") Integer uid);

    @Update("update `order` set status = '已删除' where oid = #{oid}")
    void updateOrderStatusToDeleteByOid(@Param("oid") Integer oid);

    @Update("update `order` set status = #{status} where oid = #{oid}")
    void updateOrderStatusByOid(@Param("oid") Integer oid, @Param("status") String status);

    @Update("update `order` set status = #{status}, end_time = #{end_time}, alipay_trade_no = #{alipay_trade_no} where oid = #{oid}")
    void updateOrderCompleteByOid(@Param("oid") Integer oid,  @Param("end_time") String end_time,
                                @Param("alipay_trade_no") String alipay_trade_no, @Param("status") String status);
    @Select("select * from `order` where oid = #{oid}")
    Order selectOrderByOid(@Param("oid") Integer oid);
}
