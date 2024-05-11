package com.wyer.server.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wyer.server.exception.ServiceException;
import com.wyer.server.mapper.ShopMapper;
import com.wyer.server.mapper.UserMapper;
import com.wyer.server.model.entity.Shop;
import com.wyer.server.model.entity.User;
import com.wyer.server.utils.ContextMapUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Function: 拦截器
 * Writer: wyer
 * Date: 2023/09/29 20:23
 **/
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShopMapper shopMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        if (token == null || "".equals(token)) {
            token = request.getParameter("token");
        }

        // 放行注解
        if (handler instanceof HandlerMethod) {
            AuthAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if (annotation != null) {
                return true;
            }
        }

        // 用户访问控制注解
        if (handler instanceof HandlerMethod) {
            UserAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(UserAccess.class);
            if (annotation != null) {
                // 执行认证
                if (token == null || "".equals(token)) {
                    throw new ServiceException("401", "请登录");
                }

                // 获取token中的user id
                String userId;
                try {
                    userId = JWT.decode(token).getAudience().get(0); // 解码
                } catch (JWTDecodeException j) {
                    throw new ServiceException("401", "请登录");
                }

                // 根据token中的user id查询数据库
                User user = userMapper.selectByID(Integer.valueOf(userId));
                if (user == null) {
                    throw new ServiceException("401", "请登录");
                }

                // 用户密码加签验证
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token); // 验证token
                } catch (JWTVerificationException e) {
                    throw new ServiceException("401", "请登录");
                }

                ContextMapUtils.setContextId(user.getUid());

                return true;
            }
        }

        // shop访问控制注解
        if (handler instanceof HandlerMethod) {
            ShopAccess annotation = ((HandlerMethod) handler).getMethodAnnotation(ShopAccess.class);
            if (annotation != null) {
                // 执行认证
                if (token == null || "".equals(token)) {
                    throw new ServiceException("401", "请登录");
                }

                // 获取token中的user id
                String shopId;
                try {
                    shopId = JWT.decode(token).getAudience().get(0); // 解码
                } catch (JWTDecodeException j) {
                    throw new ServiceException("401", "请登录");
                }

                // 根据token中的id查询数据库
//                Shop shop = shopMapper.selectByID(Integer.valueOf(shopId));
//                if (shop == null) {
//                    throw new ServiceException("401", "请登录");
//                }

                // 用户密码加签验证
//                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(shop.getPassword())).build();
//                try {
//                    jwtVerifier.verify(token); // 验证token
//                } catch (JWTVerificationException e) {
//                    throw new ServiceException("401", "请登录");
//                }

                ContextMapUtils.setContextId(Integer.parseInt(shopId));

                return true;
            }
        }

        throw new ServiceException("401", "请登录");
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        ContextMapUtils.removeContextId();
    }
}
