package com.wyer.server.mapper;

import com.wyer.server.model.entity.BrowseHistory;
import com.wyer.server.model.vo.BrowseHistoryCountForShop;
import com.wyer.server.model.vo.BrowseHistoryDetailsForUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Function: 浏览记录数据库接口
 * Writer: wyer
 * Date: 2023/10/23 15:43
 **/
@Mapper
public interface BrowseHistoryMapper {

    @Insert("insert into browse_history (uid, sid, gid, time, seconds) values (#{uid}, #{sid}, #{gid}, #{time}, #{seconds})")
    void add(BrowseHistory browseHistory);

    @Select("select * from browse_history where sid = #{sid}")
    List<BrowseHistory> selectBrowseHistoryBySid(@Param("sid") Integer sid);

    @Select("select * from browse_history where gid = #{gid} order by time desc limit 0, 200")
    List<BrowseHistory> selectBrowseHistoryByGid(@Param("gid") Integer gid);

    List<BrowseHistoryCountForShop> selectBrowseHistoryCountForShopBySid(@Param("sid") Integer sid);

    @Select("SELECT browse_history.id, browse_history.uid, browse_history.gid, shop.shopname, goods.`name`," +
            " browse_history.time FROM browse_history, shop, goods WHERE browse_history.uid = #{uid} AND " +
            "browse_history.sid = shop.sid AND browse_history.gid = goods.gid ORDER BY time DESC LIMIT 0, 200")
    List<BrowseHistoryDetailsForUser> selectBrowsHistoryDetailsForUserByUid(@Param("uid") Integer uid);

    List<BrowseHistoryCountForShop> selectBrowseHistoryBySalerId(@Param("sid") Integer sid, @Param("saler") Integer salerId);
}
