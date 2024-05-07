package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.model.vo.CategoryPerformance;
import com.wyer.server.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Name: CategoryController
 * Writer: wyer
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @ShopAccess
    @PostMapping("/get/shop")
    public Result getShopReportByCategory(@RequestBody Integer sid) {
        List<CategoryPerformance> categoryPerformances = categoryService.getShopPerformanceBySid(sid);
        return Result.success(categoryPerformances);
    }
}
