package com.wyer.server.mapper;

import com.wyer.server.model.entity.Saler;
import com.wyer.server.model.vo.SalerPerformance;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Name: SalerMapper
 * Writer: wyer
 **/
@Mapper
public interface SalerMapper {
    @Select("select * from saler where username = #{username} and active = 1")
    Saler selectByUsername(String username);

    @Select("select * from saler where sid = #{sid} and active = 1")
    List<Saler> selectBySid(Integer sid);

    void batchDelete(List<Integer> list);

    @Delete("update saler set active = 0 where id = #{id}")
    void delete(Integer id);

    @Update("update saler set name = #{name}, username = #{username}, password = #{password} where id = #{id}")
    void update(Saler saler);

    @Insert("insert into saler (name, username, password, sid) values (#{name}, #{username}, #{password}, #{sid})")
    void add(Saler saler);

    @Select("select saler.id, saler.name, sum(total) as total from purchase_history, goods, saler " +
            "where goods.gid = purchase_history.gid and goods.saler = saler.id and purchase_history.sid = #{sid} " +
            "group by saler.id")
    List<SalerPerformance> selectSalerPerformance(Integer sid);
}
