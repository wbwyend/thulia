package com.wyer.server.service.impl;

import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.BrowseHistoryMapper;
import com.wyer.server.mapper.GoodsMapper;
import com.wyer.server.mapper.UserMapper;
import com.wyer.server.model.entity.BrowseHistory;
import com.wyer.server.model.entity.Goods;
import com.wyer.server.model.entity.User;
import com.wyer.server.model.vo.BrowseHistoryCountForShop;
import com.wyer.server.model.vo.BrowseHistoryDetailsForShop;
import com.wyer.server.model.vo.BrowseHistoryDetailsForUser;
import com.wyer.server.service.BrowseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function: 浏览记录服务类
 * Writer: wyer
 * Date: 2023/10/23 15:48
 **/
@Service
public class BrowseHistoryServiceImpl implements BrowseHistoryService {

    @Autowired
    BrowseHistoryMapper browseHistoryMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 添加一条浏览记录
     * @param browseHistory
     */
    @Override
    public void add(BrowseHistory browseHistory) {
        long timestamp = Long.parseLong(browseHistory.getTime());
        LocalDateTime currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDateTime = currentTime.format(formatter);
        browseHistory.setTime(formattedDateTime);
        browseHistoryMapper.add(browseHistory);
    }

    /**
     * 商家获得商品的浏览记录统计结果
     * @param sid
     * @return List<BrowseHistoryCount>
     */
    @Override
    public List<BrowseHistoryCountForShop> getShop(Integer sid) {
        return browseHistoryMapper.selectBrowseHistoryCountForShopBySid(sid);
    }

    /**
     * 销售获得商品的浏览记录统计结果
     * @param sid
     * @param salerId
     * @return
     */
    @Override
    public List<BrowseHistoryCountForShop> getSaler(Integer sid, Integer salerId) {
        return browseHistoryMapper.selectBrowseHistoryBySalerId(sid, salerId);
    }

    /**
     * 商家获得商品的浏览记录详细信息
     * @param gid
     * @return List<BrowseHistoryDetails>
     */
    @Override
    public List<BrowseHistoryDetailsForShop> getShopDetails(Integer gid) {
        List<BrowseHistory> browseHistories = browseHistoryMapper.selectBrowseHistoryByGid(gid);
        List<BrowseHistoryDetailsForShop> res = new ArrayList<>();
        List<Integer> uidList = new ArrayList<>();
        for (BrowseHistory browseHistory : browseHistories) {
            uidList.add(browseHistory.getUid());
        }
        List<User> userList = userMapper.selectUserNameByUidList(uidList);
        for (int i = 0; i < browseHistories.size(); i++) {
            res.add(new BrowseHistoryDetailsForShop(browseHistories.get(i)));
            if (browseHistories.get(i).getUid().equals(0)) {
                res.get(i).setUsername("游客");
            } else {
                for (User user : userList) {
                    if (res.get(i).getUid().equals(user.getUid())) {
                        res.get(i).setUsername(user.getUsername());
                    }
                }
            }
        }
        return res;
    }

    /**
     * 用户获取浏览记录历史
     * @param uid
     * @return List<BrowseHistoryDetailsForUser>
     */
    public List<BrowseHistoryDetailsForUser> selectBrowsHistoryDetailsForUserByUid(Integer uid) {
        return browseHistoryMapper.selectBrowsHistoryDetailsForUserByUid(uid);
    }
}
