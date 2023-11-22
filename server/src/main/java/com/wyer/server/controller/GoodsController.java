package com.wyer.server.controller;

import com.wyer.server.common.AuthAccess;
import com.wyer.server.common.Result;
import com.wyer.server.common.ShopAccess;
import com.wyer.server.common.UserAccess;
import com.wyer.server.entity.BrowseHistory;
import com.wyer.server.entity.Goods;
import com.wyer.server.entity.PurchaseHistory;
import com.wyer.server.service.BrowseHistoryServiceImpl;
import com.wyer.server.service.GoodsServiceImpl;
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

    /**
     * 商家添加新商品
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
     * @param sid
     * @return List<Goods>
     */
    @AuthAccess
    @PostMapping(value = "/get/shop")
    public Result getShopGoods(@RequestBody Integer sid) {
        return Result.success(goodsService.getShopGoodsBySidAndState(sid, "true"));
    }

    /**
     * 商家下架商品
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
     * @param goods
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/update/shop")
    public Result updateShopGoods(@RequestBody Goods goods) {
        return Result.success(goodsService.updateShopGoods(goods));
    }

    /**
     * 商家获取已下架商品
     * @param sid
     * @return List<Goods>
     */
    @ShopAccess
    @PostMapping(value = "/get/outsale/shop")
    public Result getShopOutSaleGoods(@RequestBody Integer sid) {
        return Result.success(goodsService.getShopGoodsBySidAndState(sid, "false"));
    }

    /**
     * 商家上架已下架商品
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
     * @param ids
     * @return Goods
     */
    @AuthAccess
    @PostMapping(value = "/get/details")
    public Result detailsGet(@RequestBody List<Integer> ids) {
        Goods goods = goodsService.selectByGid(ids.get(0));
        if (goods.getOnsale().equals("false")) {
            return Result.error("404", "该商品已下架");
        }
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setUid(ids.get(1));
        browseHistory.setSid(goods.getSid());
        browseHistory.setGid(ids.get(0));
        browseHistoryService.add(browseHistory);
        return Result.success(goods);
    }

    /**
     * 检查商品存货是否满足用户购买
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
     * @return List<Goods>
     */
    @AuthAccess
    @GetMapping(value = "/recommend/home")
    public Result recommendFromHome() {
        return Result.success(goodsService.recommend());
    }
}
