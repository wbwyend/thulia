package com.wyer.server.service;

import com.wyer.server.common.Password;
import com.wyer.server.entity.Shop;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.ShopMapper;
import com.wyer.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Function: 商家服务类
 * Writer: wyer
 * Date: 2023/09/29 18:07
 **/
@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    ShopMapper shopMapper;

    /**
     * 商家登录
     * @param shop
     * @return Shop
     */
    @Override
    public Shop login(Shop shop) {
        Shop dbShop = shopMapper.selectByUsername(shop.getUsername());
        if (dbShop == null) {
            throw new ServiceException("账号或密码错误");
        }
        if (!shop.getPassword().equals(dbShop.getPassword())) {
            throw new ServiceException("账号或密码错误");
        }
        // 生成token
        dbShop.setToken(TokenUtils.createToken(dbShop.getSid().toString(), dbShop.getPassword()));
        //去除密码等敏感信息
        dbShop.setPassword("");
        return dbShop;
    }

    /**
     * 商家注册
     * @param shop
     */
    @Override
    public void register(Shop shop) {
        shopMapper.insertShop(shop);
    }

    /**
     * 更改商家信息
     * @param shop
     * @return Shop
     */
    @Override
    public Shop modify(Shop shop) {
        try {
            shopMapper.updateShop(shop);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                throw new ServiceException("账号重名");
            } else {
                throw new ServiceException("系统错误");
            }
        }
        Shop dbShop = shopMapper.selectByID(shop.getSid());
        dbShop.setToken(TokenUtils.createToken(dbShop.getSid().toString(), dbShop.getPassword()));
        dbShop.setPassword("");
        return dbShop;
    }

    /**
     * 更改商家密码
     * @param password
     * @return Shop
     */
    @Override
    public Shop modify(Password password) {
        Shop dbShop = shopMapper.selectByID(password.getId());
        if (!dbShop.getPassword().equals(password.getOldpassword())) {
            throw new ServiceException("原密码错误");
        }
        shopMapper.updatePassword(password.getId(), password.getNewpassword());
        dbShop.setToken(TokenUtils.createToken(dbShop.getSid().toString(), password.getNewpassword()));
        dbShop.setPassword("");
        return dbShop;
    }

    /**
     * 获得商家信息
     * @param sid
     * @return Shop
     */
    @Override
    public Shop getDetailsPageShopInformation(Integer sid) {
        try {
            Shop shop = shopMapper.selectByID(sid);
            shop.setUsername("");
            shop.setPassword("");
            return shop;
        } catch (Exception e) {
            throw new ServiceException("系统错误");
        }
    }
}
