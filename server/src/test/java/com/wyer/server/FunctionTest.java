package com.wyer.server;

import com.wyer.server.common.JwtInterceptor;
import com.wyer.server.common.Result;
import com.wyer.server.config.AlipayConfig;
import com.wyer.server.controller.WebController;
import com.wyer.server.exception.GlobalException;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.model.entity.*;
import com.wyer.server.model.vo.*;
import com.wyer.server.service.impl.*;
import com.wyer.server.utils.COSUtils;
import com.wyer.server.utils.ContextMapUtils;
import com.wyer.server.utils.IPv4Util;
import com.wyer.server.utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Name: Test
 * Writer: wyer
 **/
@SpringBootTest
@AutoConfigureMockMvc
public class FunctionTest {

    /*
     * model测试
     */
    @Test
    void modelTest() {
        System.out.println(new BrowseHistory());
        System.out.println(new Cart());
        System.out.println(new Category());
        System.out.println(new Goods());
        System.out.println(new LoginData());
        System.out.println(new Order());
        System.out.println(new PurchaseHistory());
        System.out.println(new Saler());
        System.out.println(new SalerOperation() + "" + new SalerOperation(1,1,"","", ""));
        System.out.println(new Shop());
        System.out.println(new ShopOperation() + "" + new ShopOperation(1,1,"","", ""));
        System.out.println(new User());

        System.out.println(new BrowseHistoryCountForShop() + "" + new BrowseHistoryCountForShop(new BrowseHistory()));
        System.out.println(new BrowseHistoryDetailsForShop(new BrowseHistory()));
        System.out.println(new BrowseHistoryDetailsForUser());
        System.out.println(new CartTableRow());
        System.out.println(new CategoryPerformance());
        System.out.println(new Password());
        System.out.println(new PurchaseHistoryForShopView());
        System.out.println(new PurchaseHistoryForUserPay());
        System.out.println(new PurchaseHistoryForUserView());
        System.out.println(new SalerPerformance());
        System.out.println(new SalesVolumeByDate());
        System.out.println(new UserCFObject());
        System.out.println(new UserPortrait());
    }

    /*
     * utils测试
     */
    @Autowired
    COSUtils cosUtils;

    @Test
    void utilsTest() {
        ContextMapUtils contextMapUtils = new ContextMapUtils();
        ContextMapUtils.setContextId(1);
        ContextMapUtils.getContextId();
        ContextMapUtils.removeContextId();

        File file = new File("D:\\Project\\ShoppingProject\\files\\user_avatar\\8767c26d5fee13290d2d594df97c61c_1715240515043.png");
        cosUtils.uploadObject(file, "user_avatar/dgp.png");

        IPv4Util iPv4Util = new IPv4Util();
        IPv4Util.getRequestIp();

        TokenUtils tokenUtils = new TokenUtils();
        TokenUtils.createToken("1", "123456");
    }

    /*
     * service测试
     */
    @Autowired
    BigDataServiceImpl bigDataService;
    @Autowired
    BrowseHistoryServiceImpl browseHistoryService;
    @Autowired
    CartServiceImpl cartService;
    @Autowired
    CategoryServiceImpl categoryService;
    @Autowired
    GoodsServiceImpl goodsService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    PurchaseHistoryServiceImpl purchaseHistoryService;
    @Autowired
    SalerServiceImpl salerService;
    @Autowired
    ShopServiceImpl shopService;
    @Autowired
    UserServiceImpl userService;

    @Test
    void BigDataServiceTest() {
        LoginData loginData = new LoginData();
        loginData.setUid(0);
        loginData.setTime(System.currentTimeMillis());
        loginData.setType("li");
        loginData.setIp("");
        bigDataService.saveLoginData(loginData);

        ShopOperation shopOperation = new ShopOperation();
        shopOperation.setSid(1);
        shopOperation.setTime(System.currentTimeMillis());
        shopOperation.setType("li");
        shopOperation.setIp("");
        shopOperation.setObject("");
        bigDataService.saveShopOperation(shopOperation);

        SalerOperation salerOperation = new SalerOperation();
        salerOperation.setSalerId(1);
        salerOperation.setTime(System.currentTimeMillis());
        salerOperation.setType("li");
        salerOperation.setIp("");
        salerOperation.setObject("");
        bigDataService.saveSalerOperation(salerOperation);

        bigDataService.getUserPortrait(1);

        bigDataService.getRecommendGoods(1);

        bigDataService.getSalesVolumeByDate(1);
    }

    @Test
    void BrowseHistoryServiceTest() {
        BrowseHistory browseHistory = new BrowseHistory();
        browseHistory.setUid(0);
        browseHistory.setSid(1);
        browseHistory.setGid(1);
        browseHistory.setTime(System.currentTimeMillis() + "");
        browseHistory.setSeconds(10);
        browseHistoryService.add(browseHistory);

        browseHistoryService.getShop(1);

        browseHistoryService.getSaler(1,1);

        browseHistoryService.getShopDetails(1);

        browseHistoryService.selectBrowsHistoryDetailsForUserByUid(1);
    }

    @Test
    void CartServiceTest() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setGid(1);
        cart.setNumber(1);
        try {
            cartService.add(cart);
            cartService.add(cart);
        } catch (RuntimeException e) {

        }


        cartService.get(1);

        cartService.delete(cart);

        cartService.add(cart);
        List<Cart> carts = new ArrayList<>();
        carts.add(cart);
        cartService.batchDelete(carts);

        cart.setNumber(2);
        cartService.add(cart);
        cartService.updateNumber(cart);

        cartService.batchUpdateNumber(carts);

        cartService.delete(cart);
    }

    @Test
    void CategoryServiceTest() {
        categoryService.getShopPerformanceBySid(1);
    }

    @Test
    void GoodsServiceTest() {
        Goods goods = new Goods();
        goods.setName("");
        goods.setPrice(BigDecimal.valueOf(1));
        goods.setSurplus(1);
        goods.setSales(0);
        goods.setPicture("");
        goods.setSid(1);
        goods.setOnsale("true");
        goods.setSaler(1);
        goods.setCid(1);
        goodsService.add(goods);

        goodsService.getShopGoodsBySidAndState(1, "true");

        goodsService.deleteShopGoods(goods);

        goodsService.listShopGoods(goods);

        List<Goods> goodsList = new ArrayList<>();
        goodsList.add(goods);

        goodsService.batchDeleteShopGoods(goodsList);

        goodsService.batchListShopGoods(goodsList);

        goodsService.updateShopGoods(goods);

        goodsService.updateSalerGoods(goods);

        goods.setOnsale("false");

        goodsService.updateShopGoods(goods);

        goodsService.updateSalerGoods(goods);

        goods.setOnsale("true");

        goodsService.selectByGid(1);

        List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setId(100000001);
        purchaseHistory.setUid(504);
        purchaseHistory.setSid(22);
        purchaseHistory.setGid(425);
        purchaseHistory.setNumber(1);
        purchaseHistory.setOid(100000001);
        purchaseHistory.setTotal(BigDecimal.valueOf(258));
        purchaseHistoryList.add(purchaseHistory);
        goodsService.examineSurplus(purchaseHistoryList);

        purchaseHistory.setNumber(100000);
        goodsService.examineSurplus(purchaseHistoryList);

        goodsService.recommend();

        goodsService.search("141241");

        goodsService.getSalerGoodsBySidAndState(1, "true");

    }

    @Test
    void OrderServiceTest() {
        orderService.selectOrderByOid(100000001);

        orderService.updateOrder(100000001, "2024-04-04 18:39:18.330", "");

        orderService.selectOrdersByUid(1);

        Order order = new Order();
        order.setUid(1);
        order.setOrdername("");
        order.setTotal(BigDecimal.valueOf(0));
        Integer oid = (Integer) orderService.insertOrder(order);
        List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUid(1);
        purchaseHistory.setSid(1);
        purchaseHistory.setGid(1);
        purchaseHistory.setNumber(1);
        purchaseHistory.setOid(oid);
        purchaseHistory.setTotal(BigDecimal.valueOf(984));
        purchaseHistoryList.add(purchaseHistory);
        purchaseHistoryService.batchInsertPurchaseHistory(purchaseHistoryList);

        orderService.delete(oid, 1);
    }

    @Test
    void PurchaseHistoryServiceTest() {
        Order order = new Order();
        order.setUid(1);
        order.setOrdername("");
        order.setTotal(BigDecimal.valueOf(0));
        Integer oid = (Integer) orderService.insertOrder(order);
        List<PurchaseHistory> purchaseHistoryList = new ArrayList<>();
        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUid(1);
        purchaseHistory.setSid(1);
        purchaseHistory.setGid(1);
        purchaseHistory.setNumber(1);
        purchaseHistory.setOid(oid);
        purchaseHistory.setTotal(BigDecimal.valueOf(984));
        purchaseHistoryList.add(purchaseHistory);
        purchaseHistoryService.batchInsertPurchaseHistory(purchaseHistoryList);

        purchaseHistoryService.selectPurchaseHistoryByOid(oid);

        purchaseHistoryService.selectPurchaseHistoryByUid(1);

        purchaseHistoryService.selectPurchaseHistoryBySid(1);

        purchaseHistoryService.selectPurchaseHistoryBySalerId(1);
    }

    @Test
    void SalerServiceTest() {
        Saler saler = new Saler();
        saler.setUsername("saler1");
        saler.setPassword("123456");
        salerService.login(saler);

        try {
            saler.setUsername("saler1");
            saler.setPassword("12345");
            salerService.login(saler);
        } catch (Exception e) {}

        try {
            saler.setUsername("saler0");
            saler.setPassword("123456");
            salerService.login(saler);
        } catch (Exception e) {}

        salerService.getBySid(1);

        saler.setUsername("saler" + UUID.randomUUID());
        saler.setName("saler121");
        saler.setSid(1);
        saler.setActive(1);
        saler.setPassword("123456");
        salerService.add(saler);

        salerService.update(saler);

        List<Saler> salerList = new ArrayList<>();
        salerList.add(saler);
        salerService.batchDelete(salerList);

        saler.setUsername("saler" + UUID.randomUUID());
        salerService.add(saler);
        salerService.delete(saler);

        salerService.getSalerPerformance(1);
    }

    @Test
    void ShopServiceTest() {
        Shop shop = new Shop();


        try {
            shop.setUsername("shop2");
            shop.setPassword("12345");
            shopService.login(shop);
        } catch (Exception e) {}

        try {
            shop.setUsername("shop31");
            shop.setPassword("123456");
            shopService.login(shop);
        } catch (Exception e) {}

        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);

        shop.setUsername("shop" + UUID.randomUUID());
        shop.setShopname("shop31");
        shop.setPassword("123456");
        shop.setPhone("");
        shop.setEmail("");
        shop.setAddress("");
        shop.setAvatar("");
        shopService.register(shop);

        shop = shopService.login(shop);

        try {
            shopService.register(shop);
        } catch (Exception e) {}

        shop = shopService.modify(shop);

        String temp = shop.getUsername();
        try {
            shop.setUsername("shop2");
            shopService.modify(shop);
        } catch (Exception e) {}
        shop.setUsername(temp);

        Password password = new Password();
        password.setId(shop.getSid());
        password.setOldpassword("12345");
        password.setNewpassword("123456");
        try {
            shopService.modify(password);
        } catch (Exception e) {}
        password.setOldpassword("123456");
        shopService.modify(password);

        shopService.getDetailsPageShopInformation(1);
    }

    @Test
    void UserServiceTest() {
        User user = new User();
        String username = "user" + UUID.randomUUID();
        user.setUsername(username);
        user.setName("user1001");
        user.setPassword("123456");

        userService.register(user);
        try {
            userService.register(user);
        } catch (Exception e) {}


        try {
            user.setUsername("user0");
            userService.login(user);
        } catch (Exception e) {}
        try {
            user.setUsername(username);
            user.setPassword("12345");
            userService.login(user);
        } catch (Exception e) {}
        try {
            user.setUsername("deleted_user");
            user.setPassword("123456");
            userService.login(user);
        } catch (Exception e) {}
        user.setUsername(username);
        user.setPassword("123456");
        user = userService.login(user);

        userService.modify(user);
        try {
            user.setUsername("user2");
            userService.modify(user);
        } catch (Exception e) {}

        Password password = new Password();
        password.setId(user.getUid());
        password.setOldpassword("12345");
        password.setNewpassword("123456");
        try {
            userService.modify(password);
        } catch (Exception e) {}
        password.setOldpassword("123456");
        userService.modify(password);

        userService.closeUser(user.getUid());
    }

    /*
     * exception测试
     */
    @Test
    void exceptionTest() {
        try {
            throw new ServiceException("");
        } catch (ServiceException e) {
            System.out.println(e.getCode());
        }

        try {
            throw new ServiceException("500", "");
        } catch (Exception e) {}

        GlobalException globalException = new GlobalException();
        globalException.serviceException(new ServiceException(""));
        globalException.globalException(new Exception());
    }

    /*
     * config测试
     */
    @Autowired
    AlipayConfig alipayConfig;

    @Test
    void configTest() {
        alipayConfig.getAppId();
        alipayConfig.getAppPrivateKey();
        alipayConfig.getAliPublicKey();
        alipayConfig.getNotifyUrl();
    }

    /*
     * common测试
     */
    @Autowired
    public JwtInterceptor jwtInterceptor;

    @Test
    void commonTest() throws Exception {
        System.out.println(new Result());
        Result.success();
        Result.success(new Object());
        Result.error("");
        Result.error("500", "");
        Result.error();
        Result.builder().build();

        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);

        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/get/user/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/get/user/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/get/user/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", "wevwvagaibibeviyb"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String token = TokenUtils.createToken("1000000", "123456");
        mockMvc.perform(MockMvcRequestBuilders.get("/get/user/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        token = TokenUtils.createToken("2", "12345");
        mockMvc.perform(MockMvcRequestBuilders.get("/get/user/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

//        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("token", shop.getToken()))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", "wevwvagaibibeviyb"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        token = TokenUtils.createToken("1000000", "123456");
        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        token = TokenUtils.createToken("2", "12345");
        mockMvc.perform(MockMvcRequestBuilders.get("/get/shop/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", "123456789"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }


    /*
     * controller测试
     */

    @Autowired
    MockMvc mockMvc;

    @Test
    void BrowseHistoryControllerTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/browse/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"sid\":1,\"gid\":1,\"time\":1715258938915,\"seconds\":22.164}\n"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);
        mockMvc.perform(MockMvcRequestBuilders.post("/browse/get/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.get("/browse/get/saler?sid=1&saler=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/browse/get/shop/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/browse/get/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("2")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void CartControllerTest() throws Exception {

        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/cart/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"gid\":\"1\",\"number\":1}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/cart/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());




        mockMvc.perform(MockMvcRequestBuilders.post("/cart/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"gid\":\"1\",\"number\":1}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Cart cart = new Cart();
        cart.setUid(1);
        cart.setGid(2);
        cart.setNumber(1);
        cartService.add(cart);
        cart.setGid(1);
        cartService.add(cart);
        mockMvc.perform(MockMvcRequestBuilders.post("/cart/batch/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"uid\":1,\"gid\":1,\"number\":1},{\"uid\":1,\"gid\":2,\"number\":1}]")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        cartService.add(cart);
        cart.setGid(2);
        cartService.add(cart);
        mockMvc.perform(MockMvcRequestBuilders.post("/cart/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"gid\":\"1\",\"number\":2}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/cart/batch/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"uid\":1,\"gid\":1,\"number\":3},{\"uid\":1,\"gid\":2,\"number\":3}]")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/cart/batch/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"uid\":1,\"gid\":1,\"number\":3},{\"uid\":1,\"gid\":2,\"number\":3}]")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    void CategoryControllerTest() throws Exception {
        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);
        mockMvc.perform(MockMvcRequestBuilders.post("/category/get/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void GoodsControllerTest() throws Exception {
        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);

//        Goods goods = new Goods();
//        goods.setName("");
//        goods.setPrice(BigDecimal.valueOf(1));
//        goods.setSurplus(1);
//        goods.setSales(0);
//        goods.setPicture("");
//        goods.setSid(1);
//        goods.setOnsale("true");
//        goods.setSaler(1);
//        goods.setCid(1);

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/add/shop")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"name\": \"shop1的商品p\",\n" +
                        "        \"price\": 10.00,\n" +
                        "        \"surplus\": 200,\n" +
                        "        \"sales\": 0,\n" +
                        "        \"picture\": \"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\n" +
                        "        \"sid\": 1,\n" +
                        "        \"onsale\": \"true\",\n" +
                        "        \"saler\": 1,\n" +
                        "        \"cid\": 1\n" +
                        "    }")
                .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/shop")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1")
                .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/delete/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"gid\": 101,\n" +
                                "        \"name\": \"shop6的商品101\",\n" +
                                "        \"price\": 911.00,\n" +
                                "        \"surplus\": 185,\n" +
                                "        \"sales\": 195,\n" +
                                "        \"picture\": \"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\n" +
                                "        \"sid\": 6,\n" +
                                "        \"onsale\": \"true\",\n" +
                                "        \"saler\": 21,\n" +
                                "        \"cid\": 3\n" +
                                "    }")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("101")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/list/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"gid\": 101,\n" +
                                "        \"name\": \"shop6的商品101\",\n" +
                                "        \"price\": 911.00,\n" +
                                "        \"surplus\": 185,\n" +
                                "        \"sales\": 195,\n" +
                                "        \"picture\": \"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\n" +
                                "        \"sid\": 6,\n" +
                                "        \"onsale\": \"true\",\n" +
                                "        \"saler\": 21,\n" +
                                "        \"cid\": 3\n" +
                                "    }")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        mockMvc.perform(MockMvcRequestBuilders.post("/goods/batch/delete/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"gid\":1,\"name\":\"shop1的商品1\",\"price\":984,\"surplus\":105,\"sales\":165,\"picture\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\"sid\":1,\"onsale\":\"true\",\"saler\":1,\"cid\":8},{\"gid\":2,\"name\":\"shop1的商品2\",\"price\":92,\"surplus\":63,\"sales\":194,\"picture\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\"sid\":1,\"onsale\":\"true\",\"saler\":1,\"cid\":7}]")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/batch/list/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"gid\":1,\"name\":\"shop1的商品1\",\"price\":984,\"surplus\":105,\"sales\":165,\"picture\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\"sid\":1,\"onsale\":\"true\",\"saler\":1,\"cid\":8},{\"gid\":2,\"name\":\"shop1的商品2\",\"price\":92,\"surplus\":63,\"sales\":194,\"picture\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\"sid\":1,\"onsale\":\"true\",\"saler\":1,\"cid\":7}]")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/update/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"gid\": 101,\n" +
                                "        \"name\": \"shop6的商品101\",\n" +
                                "        \"price\": 911.00,\n" +
                                "        \"surplus\": 185,\n" +
                                "        \"sales\": 195,\n" +
                                "        \"picture\": \"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\n" +
                                "        \"sid\": 6,\n" +
                                "        \"onsale\": \"true\",\n" +
                                "        \"saler\": 21,\n" +
                                "        \"cid\": 3\n" +
                                "    }")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/update/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "        \"gid\": 101,\n" +
                                "        \"name\": \"shop6的商品101\",\n" +
                                "        \"price\": 911.00,\n" +
                                "        \"surplus\": 185,\n" +
                                "        \"sales\": 195,\n" +
                                "        \"picture\": \"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/goods_picture/default_goods_picture.jpg\",\n" +
                                "        \"sid\": 6,\n" +
                                "        \"onsale\": \"true\",\n" +
                                "        \"saler\": 21,\n" +
                                "        \"cid\": 3\n" +
                                "    }")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/outsale/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/outsale/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/goods/get/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/goods/examine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"gid\":101,\"number\":3}]")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.get("/goods/recommend/home/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.get("/goods/get/sales/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void OrderControllerTest() throws Exception {
        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/order/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/order/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"total\":2733,\"ordername\":\"包含shop6的商品101X3的订单\"}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


        Order order = new Order();
        order.setUid(1);
        order.setOrdername("包含shop6的商品101X3的订单");
        order.setTotal(BigDecimal.valueOf(2733));
        Integer oid = (Integer) orderService.insertOrder(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/order/delete?oid=" + oid + "&uid=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void PurchaseHistoryControllerTest() throws Exception {
        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/purchase/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"uid\":1,\"sid\":6,\"gid\":\"101\",\"number\":3,\"oid\":100109108,\"total\":2733}]")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/purchase/select")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("100109108")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/purchase/get/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);
        mockMvc.perform(MockMvcRequestBuilders.post("/purchase/get/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/purchase/get/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void SalerControllerTest() throws Exception {
        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);

        mockMvc.perform(MockMvcRequestBuilders.post("/saler/get/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String salerUserName = "saler" + UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.post("/saler/add/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"salerp\",\"username\":\"" + salerUserName + "\",\"password\":\"123456\",\"sid\":1}")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/saler/update/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":127,\"name\":\"salerp\",\"username\":\"salerp\",\"password\":\"123456\",\"sid\":1,\"active\":1,\"token\":null}")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/saler/delete/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":127,\"name\":\"salerp\",\"username\":\"salerp\",\"password\":\"123456\",\"sid\":1,\"active\":1,\"token\":null}")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/saler/batch/delete/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"id\":127,\"name\":\"salerp\",\"username\":\"salerp\",\"password\":\"123456\",\"sid\":1,\"active\":1,\"token\":null}]")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/saler/get/performance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void ShopControllerTest() throws Exception {
        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);
        mockMvc.perform(MockMvcRequestBuilders.post("/shop/modify/information")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sid\":1,\"username\":\"admin\",\"password\":\"\",\"shopname\":\"admin\",\"phone\":\"admin\",\"email\":\"admin@admin\",\"address\":\"河北/唐山\",\"avatar\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/shop_avatar/default_shop_avatar.jpg\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNzE1MzI0NDkyfQ.0AORDL0LmaUqnWQZFsj-O9MLtse3c_Kdy6C3O4Mo7nk\"}\n")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/shop/modify/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"oldpassword\":\"admin\",\"newpassword\":\"admin\",\"secondpassword\":\"admin\"}")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/shop/get/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1")
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    void UserControllerTest() throws Exception {
        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/modify/information")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":1,\"username\":\"admin\",\"password\":\"\",\"name\":\"admin\",\"phone\":\"16715648565\",\"email\":\"16715648565@sp.com\",\"address\":\"陕西/宝鸡\",\"avatar\":\"https://wbwy-1318322348.cos.ap-guangzhou.myqcloud.com/user_avatar/default_avatar.jpg\",\"token\":\"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIiwiZXhwIjoxNzE1MzI1NzgxfQ.5snILNV7YQ8ZDv3b3X8XvZzs1gIBvkL_u28ThIQ40Ow\"}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/user/modify/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"oldpassword\":\"admin\",\"newpassword\":\"admin\",\"secondpassword\":\"admin\"}")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String username = "user" + UUID.randomUUID();
        user.setUsername(username);
        user.setName("user1001");
        user.setPassword("123456");
        userService.register(user);
        user = userService.login(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.getUid() + "")
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/user/portrait")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Autowired
    WebController webController;

    @Test
    void WebControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/login/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"saler1\",\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"saler1\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/logout/saler")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\",\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"user2\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String username = "user" + UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\""+ username +"\",\"password\":\"123456\",\"email\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"123456\",\"email\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\""+ username +"\",\"email\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/login/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"shop2\",\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"123456\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/login/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"shop2\",\"validcode\":\"ztyt\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/logout/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        String shopUsername = "shop" + UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.post("/register/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\""+ username +"\",\"password\":\"123456\",\"email\":\"\",\"shopname\":\"" + shopUsername + "\",\"phone\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/register/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"123456\",\"email\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.post("/register/shop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\""+ username +"\",\"email\":\"\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("123456789"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        webController.post();
    }

    @Test
    void FileControllerTest() throws Exception {
        User user = new User();
        user.setUsername("user2");
        user.setPassword("123456");
        user = userService.login(user);

        Shop shop = new Shop();
        shop.setUsername("shop2");
        shop.setPassword("123456");
        shop = shopService.login(shop);

        File file = new File("C:\\Users\\WBWY\\Desktop\\default_shop_avatar.jpg");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file",
                "default_shop_avatar.jpg",
                MediaType.MULTIPART_FORM_DATA_VALUE,
                Files.newInputStream(file.toPath()));
        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/file/upload/avatar/shop")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/file/upload/avatar/user")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("token", user.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        mockMvc.perform(MockMvcRequestBuilders.multipart(HttpMethod.POST, "/file/upload/picture/goods")
                        .file(mockMultipartFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                        .header("token", shop.getToken()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void AlipayControllerTest() throws Exception {

        Order order = new Order();
        order.setUid(1);
        order.setOrdername("包含shop6的商品101X3的订单");
        order.setTotal(BigDecimal.valueOf(2733));
        Integer oid = (Integer) orderService.insertOrder(order);

        mockMvc.perform(MockMvcRequestBuilders.get("/alipay/pay?oid=" + oid))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/alipay/notify"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders.post("/alipay/notify")
                        .param("trade_status", "TRADE_SUCCESS")
                        .param("test", "TEST"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


}
