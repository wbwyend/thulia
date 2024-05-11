package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.model.entity.Goods;
import com.wyer.server.model.entity.PurchaseHistory;
import com.wyer.server.service.impl.BigDataServiceImpl;
import com.wyer.server.service.impl.BrowseHistoryServiceImpl;
import com.wyer.server.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Function: 商品接口类
 * Writer: wyer
 * Date: 2023/10/19 12:05
 **/
@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    BrowseHistoryServiceImpl browseHistoryService;

    @Autowired
    BigDataServiceImpl bigDataService;

    /**
     * 商家添加新商品
     *
     * @param goods
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/add/shop")
    public Result addGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.add(goods));
    }

    /**
     * 获得商家的所有在售商品
     *
     * @param sid
     * @return List<Goods>
     */
    @AuthAccess
    @PostMapping(value = "/get/shop")
    public Result getShopGoods(@RequestBody Integer sid) {
        return Result.success(goodsService.getShopGoodsBySidAndState(sid, "true"));
    }

    /**
     * 获得销售人员的所有在售商品
     *
     * @param id
     * @return List<Goods>
     */
    @AuthAccess
    @PostMapping(value = "/get/saler")
    public Result getSalerGoods(@RequestBody Integer id) {
        return Result.success(goodsService.getSalerGoodsBySidAndState(id, "true"));
    }

    /**
     * 商家下架商品
     *
     * @param goods
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/delete/shop")
    public Result deleteShopGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.deleteShopGoods(goods));
    }

    /**
     * 商家下架复数商品
     *
     * @param goodsList
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/batch/delete/shop")
    public Result batchDeleteShopGoods(@RequestBody List<Goods> goodsList) {
        return Result.success(goodsService.batchDeleteShopGoods(goodsList));
    }

    /**
     * 商家更新商品信息
     *
     * @param goods
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/update/shop")
    public Result updateShopGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.updateShopGoods(goods));
    }


    /**
     * 销售更新商品信息
     *
     * @param goods
     * @return
     */
    @ShopAccess
    @PostMapping(value = "/update/saler")
    public Result updateSalerGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.updateSalerGoods(goods));
    }

    /**
     * 商家获取已下架商品
     *
     * @param sid
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/get/outsale/shop")
    public Result getShopOutSaleGoods(@RequestBody Integer sid) {
        return Result.success(goodsService.getShopGoodsBySidAndState(sid, "false"));
    }

    /**
     * 销售获取已下架商品
     *
     * @param id
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/get/outsale/saler")
    public Result getSalerOutSaleGoods(@RequestBody Integer id) {
        return Result.success(goodsService.getSalerGoodsBySidAndState(id, "false"));
    }

    /**
     * 商家上架已下架商品
     *
     * @param goods
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/list/shop")
    public Result listShopGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.listShopGoods(goods));
    }

    /**
     * 商家批量上架复数已下架商品
     *
     * @param goodsList
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/batch/list/shop")
    public Result batchListShopGoods(@RequestBody List<Goods> goodsList) {
        return Result.success(goodsService.batchListShopGoods(goodsList));
    }

    /**
     * 商品详情页获取商品详细信息
     *
     * @param gid
     * @return Goods
     */
    @AuthAccess
    @PostMapping(value = "/get/details")
    public Result detailsGet(@RequestBody Integer gid) {
        Goods goods = goodsService.selectByGid(gid);
        if (goods.getOnsale().equals("false")) {
            return Result.error("404", "该商品已下架");
        }
        return Result.success(goods);
    }

    /**
     * 检查商品存货是否满足用户购买
     *
     * @param list
     * @return String
     */
    @UserAccess
    @PostMapping(value = "/examine")
    public Result examineSurplus(@RequestBody List<PurchaseHistory> list) {
        return Result.success(goodsService.examineSurplus(list));
    }

    /**
     * 首页推荐商品
     *
     * @return List<Goods>
     */
    @AuthAccess
    @GetMapping(value = "/recommend/home/{uid}")
    public Result recommendFromHome(@PathVariable("uid") int userId) {
        return Result.success(bigDataService.getRecommendGoods(userId));
    }

    @ShopAccess
    @GetMapping(value = "/get/sales/{gid}")
    public Result getSalesVolumeByDate(@PathVariable("gid") int gid) {
        return Result.success(bigDataService.getSalesVolumeByDate(gid));
    }
}
