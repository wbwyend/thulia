package com.wyer.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wyer.server.mapper.UserMapper;
import com.wyer.server.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

/**
 * Function: token工具类
 * Writer: wyer
 * Date: 2023/09/29 21:02
 **/
@Component
public class TokenUtils {
    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserMapper() {
        staticUserMapper = userMapper;
    }

    /**
     * 生成token
     * @return token
     */
    public static String createToken(String id, String sign) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+2); // 向后推迟两小时
        Date time = calendar.getTime();
        return JWT.create().withAudience(id) // 将 id 保存到 token 里面,作为载荷
                .withExpiresAt(time) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

//    /**
//     * 获取当前登录的用户信息
//     * @return user对象
//     *  /user?token=xxxx
//     */
//    public static User getCurrentUser() {
//        String token = null;
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            token = request.getHeader("token");
//            if (token.equals("")) {
//                token = request.getParameter("token");
//            }
//            if (!token.equals("")) {
//                String userID = JWT.decode(token).getAudience().get(0);
//                return staticUserMapper.selectByID(Integer.valueOf(userID));
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }
}
