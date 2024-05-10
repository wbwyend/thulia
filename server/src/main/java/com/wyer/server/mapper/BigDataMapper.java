package com.wyer.server.mapper;

import com.wyer.server.model.entity.LoginData;
import com.wyer.server.model.entity.SalerOperation;
import com.wyer.server.model.entity.ShopOperation;
import com.wyer.server.model.vo.SalesVolumeByDate;
import com.wyer.server.model.vo.UserCFObject;
import com.wyer.server.model.vo.UserPortrait;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Name: LoginDataMapper
 * Writer: wyer
 **/
@Mapper
public interface BigDataMapper {

    @Insert("insert into login (uid, time, ip, type) values (#{uid}, #{time}, #{ip}, #{type})")
    void saveLoginData(LoginData loginData);

    @Insert("insert into shop_operation (sid, time, ip, type, object) values (#{sid}, #{time}, #{ip}, #{type}, #{object})")
    void saveShopOperation(ShopOperation shopOperation);

    @Insert("insert into saler_operation (saler_id, time, ip, type, object) values (#{salerId}, #{time}, #{ip}, #{type}, #{object})")
    void saveSalerOperation(SalerOperation salerOperation);

//    @Insert("insert into `address` (address, province, region) values (#{address}, #{province}, #{region})")
//    void insertAddress(@Param("address") String address, @Param("province") String province, @Param("region") String region);

    @Select("select * from user_portrait where uid = #{userId}")
    UserPortrait getUserPortrait(int userId);

    @Select("select uid, gid, count(gid) as score " +
            "from browse_history_data " +
            "group by uid, gid " +
            "order by uid asc, gid asc ")
    List<UserCFObject> getBrowseHistoryData();

    @Select("select uid, gid, count(gid) as score " +
            "from purchase_history_data " +
            "group by uid, gid " +
            "order by uid asc, gid asc ")
    List<UserCFObject> getPurChaseHistoryData();

    @Select("select count(*) from user")
    int getCountUser();

    @Select("select count(*) from goods")
    int getCountGoods();

    List<Integer> getRecommendGoods(@Param("uidList") List<Integer> uidList, @Param("ratio") int ratio);

    @Select("select substr(`time`, 1, 10) as date, count(gid) as sales " +
            "from purchase_history_data " +
            "where gid = #{gid} " +
            "group by substr(`time`, 1, 10)")
    List<SalesVolumeByDate> getSalesVolumeByDate(int gid);
}
