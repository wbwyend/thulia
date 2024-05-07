package com.wyer.server.service;

import com.wyer.server.model.vo.CategoryPerformance;

import java.util.List;

/**
 * Name: CategoryService
 * Writer: wyer
 **/
public interface CategoryService {
    List<CategoryPerformance> getShopPerformanceBySid(Integer sid);
}
