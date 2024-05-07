package com.wyer.server.mapper;

import com.wyer.server.model.entity.Category;
import com.wyer.server.model.vo.CategoryPerformance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Name: CategoryMapper
 * Writer: wyer
 **/
@Mapper
public interface CategoryMapper {
    @Insert("insert into category (name) values (#{name})")
    void add(Category category);

    @Select("select category.cid as cid, category.name as name, sum(total) as total " +
            "from category, goods, purchase_history where category.cid = goods.cid and " +
            "purchase_history.gid = goods.gid and purchase_history.sid = #{sid} group by goods.cid;")
    List<CategoryPerformance> selectCategoryPerformanceBySid(Integer sid);
}
