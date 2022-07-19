package com.chengxiang.mango.util;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 程祥
 * @date 2022/7/19 10:03
 */

/**
 * 当我们通过request获取客户端IP时，自身服务器通常会为了保护信息或者负载均衡的目的，对自身服务器做反向代理。
 * 此时如果我们通过request.getRemoteAddr();可能获取到的是自身代理服务器的IP，而无法达到获取用户请求ip的目的。
 * X-Forwarded-For：Squid 服务代理
 *
 * Proxy-Client-IP：apache 服务代理
 *
 * WL-Proxy-Client-IP：weblogic 服务代理
 *
 * HTTP_CLIENT_IP：有些代理服务器
 *
 * X-Real-IP：nginx服务代理
 */
public class IPUtil {

    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if(StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        // 如果没有获取到代理IP
        if(StringUtils.isEmpty(ip) || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
