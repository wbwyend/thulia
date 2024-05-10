package com.wyer.server;

import com.wyer.server.model.vo.UserCFObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Name: TestMapper
 * Writer: wyer
 **/
@Mapper
public interface TestMapper {

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


}
