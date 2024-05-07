package com.wyer.server.controller;

import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.model.entity.Saler;
import com.wyer.server.model.vo.SalerPerformance;
import com.wyer.server.service.impl.SalerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Name: SalerController
 * Writer: wyer
 **/
@RestController
@RequestMapping("/saler")
public class SalerController {

    @Autowired
    private SalerServiceImpl salerService;

    @ShopAccess
    @PostMapping("/get/shop")
    public Result getBySid(@RequestBody Integer sid) {
        List<Saler> salerList = salerService.getBySid(sid);
        return Result.success(salerList);
    }

    @ShopAccess
    @PostMapping("/delete/shop")
    public Result deleteBySid(@RequestBody Saler saler) {
        List<Saler> salerList = salerService.delete(saler);
        return Result.success(salerList);
    }

    @ShopAccess
    @PostMapping("/batch/delete/shop")
    public Result batchDeleteBySid(@RequestBody List<Saler> salerList) {
        List<Saler> salers = salerService.batchDelete(salerList);
        return Result.success(salers);
    }

    @ShopAccess
    @PostMapping("/update/shop")
    public Result update(@RequestBody Saler saler) {
        List<Saler> salers = salerService.update(saler);
        return Result.success(salers);
    }

    @ShopAccess
    @PostMapping("/add/shop")
    public Result add(@RequestBody Saler saler) {
        List<Saler> salers = salerService.add(saler);
        return Result.success(salers);
    }

    @ShopAccess
    @PostMapping("/get/performance")
    public Result getSalerPerformance(@RequestBody Integer sid) {
        List<SalerPerformance> salerPerformances = salerService.getSalerPerformance(sid);
        return Result.success(salerPerformances);
    }

}
