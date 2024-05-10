package com.wyer.server.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * Name: IPv4Util
 * Writer: vnjohn
 **/
public class IPv4Util {

//    private static final String HEADER_FORWARDED_FOR = "x-forwarded-for";
//    private static final String HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
//    private static final String HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
//    private static final String HEADER_HTTP_CLIENT_IP = "http_client_ip";
//    private static final String HEADER_HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
//
//    private static final String UNKNOWN = "unknown";
//    private static final String CHAR_COLON = ":";
//    private static final String CHAR_COMMA = ",";

//    public static String getClientIp(HttpServletRequest request) {
//        String ip = request.getHeader(HEADER_FORWARDED_FOR);
//        boolean ipIsEmpty = ip == null || ip.length() == 0;
//        if (ipIsEmpty || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader(HEADER_PROXY_CLIENT_IP);
//        }
//        if (ipIsEmpty || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader(HEADER_WL_PROXY_CLIENT_IP);
//        }
//        if (ipIsEmpty || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        if (ipIsEmpty || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader(HEADER_HTTP_CLIENT_IP);
//        }
//        if (ipIsEmpty || UNKNOWN.equalsIgnoreCase(ip)) {
//            ip = request.getHeader(HEADER_HTTP_X_FORWARDED_FOR);
//        }
//        // 如果是多级代理，那么取第一个ip为客户ip
//        if (ip != null && ip.contains(CHAR_COMMA)) {
//            ip = ip.substring(ip.lastIndexOf(CHAR_COMMA) + 1, ip.length()).trim();
//        }
//        //判断IP是否存在带有端口号的情况、应该要去掉端口号
//        if (ip != null && ip.contains(CHAR_COLON)) {
//            ip = ip.substring(0, ip.indexOf(CHAR_COLON));
//        }
//        return ip;
//    }

    /**
     * 获取请求的ip
     */
    public static String getRequestIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取 RequestAttributes 中获取 HttpServletRequest 信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

