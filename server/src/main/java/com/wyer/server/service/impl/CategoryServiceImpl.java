package com.wyer.server.service.impl;

import com.wyer.server.mapper.CategoryMapper;
import com.wyer.server.model.vo.CategoryPerformance;
import com.wyer.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Name: CategoryServiceImpl
 * Writer: wyer
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryPerformance> getShopPerformanceBySid(Integer sid) {
        return categoryMapper.selectCategoryPerformanceBySid(sid);
    }
}
