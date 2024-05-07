package com.wyer.server.service.impl;

import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.BigDataMapper;
import com.wyer.server.mapper.SalerMapper;
import com.wyer.server.model.entity.Saler;
import com.wyer.server.model.entity.ShopOperation;
import com.wyer.server.model.vo.SalerPerformance;
import com.wyer.server.service.SalerService;
import com.wyer.server.utils.ContextMapUtils;
import com.wyer.server.utils.IPv4Util;
import com.wyer.server.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: SalerServiceImpl
 * Writer: wyer
 **/
@Service
public class SalerServiceImpl implements SalerService {

    @Autowired
    private SalerMapper salerMapper;

    @Autowired
    private BigDataMapper bigDataMapper;

    /**
     * 登录
     * @param saler
     * @return
     */
    @Override
    public Saler login(Saler saler) {
        Saler dbSaler = salerMapper.selectByUsername(saler.getUsername());
        if (dbSaler == null) {
            throw new ServiceException("账号或密码错误");
        }
        if (!saler.getPassword().equals(dbSaler.getPassword())) {
            throw new ServiceException("账号或密码错误");
        }
        if (dbSaler.getUsername().equals("deleted_user")) {
            throw new ServiceException("账号或密码错误");
        }
        // 生成token
        dbSaler.setToken(TokenUtils.createToken(String.valueOf(dbSaler.getId()), dbSaler.getPassword()));
        //去除密码等敏感信息
        dbSaler.setPassword("");
        return dbSaler;
    }

    /**
     * 根据商家ID查询销售人员
     * @param sid
     * @return
     */
    @Override
    public List<Saler> getBySid(Integer sid) {
        return salerMapper.selectBySid(sid);
    }

    /**
     * 批量删除销售人员账号
     * @param salerList
     * @return
     */
    @Override
    @Transactional
    public List<Saler> batchDelete(List<Saler> salerList) {
        List<Integer> list = new ArrayList<>();
        for (Saler saler : salerList) {
            list.add(saler.getId());
        }
        salerMapper.batchDelete(list);
        bigDataMapper.saveShopOperation(new ShopOperation(ContextMapUtils.getContextId(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "del",
                "saler"));
        return salerMapper.selectBySid(list.get(0));
    }

    /**
     * 删除销售人员账号
     * @param saler
     * @return
     */
    @Override
    @Transactional
    public List<Saler> delete(Saler saler) {
        salerMapper.delete(saler.getId());
        bigDataMapper.saveShopOperation(new ShopOperation(ContextMapUtils.getContextId(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "del",
                "saler"));
        return salerMapper.selectBySid(saler.getSid());
    }

    /**
     * 更新售人员信息账号
     * @param saler
     * @return
     */
    @Override
    @Transactional
    public List<Saler> update(Saler saler) {
        salerMapper.update(saler);
        bigDataMapper.saveShopOperation(new ShopOperation(ContextMapUtils.getContextId(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "upd",
                "saler"));
        return salerMapper.selectBySid(saler.getSid());
    }

    /**
     * 添加新的销售人员信息
     * @param saler
     * @return
     */
    @Transactional
    public List<Saler> add(Saler saler) {
        salerMapper.add(saler);
        bigDataMapper.saveShopOperation(new ShopOperation(ContextMapUtils.getContextId(),
                System.currentTimeMillis(),
                IPv4Util.getRequestIp(),
                "add",
                "saler"));
        return salerMapper.selectBySid(saler.getSid());
    }

    /**
     * 获得销售人员业绩
     * @param sid
     * @return
     */
    public List<SalerPerformance> getSalerPerformance(Integer sid) {
        return salerMapper.selectSalerPerformance(sid);
    }
}
