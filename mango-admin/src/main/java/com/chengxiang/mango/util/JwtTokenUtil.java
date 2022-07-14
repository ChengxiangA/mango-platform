package com.chengxiang.mango.util;

import com.chengxiang.mango.constant.SecurityConstants;
import com.chengxiang.mango.security.JwtAuthenticationToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名称
     */
    public static final String USERNAME = Claims.SUBJECT;

    /**
     * 创建时间
     */
    public static final String CREATED = "created";


    /**
     * 权限列表
     */
    public static final String AUTHORITIES = "authorities";

    /**
     * 有效期12小时
     */
    private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;


    /**
     * 生成Token
     * @param authentication
     * @return
     */
    public static String generateToken(Authentication authentication) {
        HashMap<String, Object> claims = new HashMap<>(3);
        claims.put(USERNAME,SecurityUtil.getUsername(authentication));
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES,authentication.getAuthorities());
        return generateToken(claims);
    }

    public static String generateToken(Map<String,Object> claims) {
        return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                    .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET_KEY)
                    .compact();
    }

    /**
     * 逆向解析token获取username
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 刷新Token过期时间
     * @param token
     * @return
     */
    public static String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME));
        String refreshToken = generateToken(claims);
        return refreshToken;
    }


    /**
     * 根据请求Token获取 Authentication
     * @param request
     * @return
     */
    public static Authentication getAuthenticationFromToken(HttpServletRequest request) {
        Authentication authentication = null;
        // 获取请求携带的令牌
        String token = JwtTokenUtil.getToken(request);
        if(token != null) {
            // 上下文路径 Authentication 为空
            if(SecurityUtil.getAuthentication() == null) {
                Claims claims = getClaimsFromToken(token);
                if(claims == null || claims.getSubject() == null || isTokenExpired(token)) {
                    return null;
                }
                List authorities = (List) claims.get(AUTHORITIES);
                authentication = new JwtAuthenticationToken(claims.getSubject(),null,authorities,token);
            } else {
                // 上下文已经存在 authentication
                authentication = SecurityUtil.getAuthentication();
            }
        }
        return authentication;
    }

    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET_KEY)
                .parseClaimsJwt(token)
                .getBody();
    }

    /**
     * 判断Token是否过期
     * @param token
     * @return
     */
    public static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        boolean isTokenExpired = new Date().before(expiration);
        return isTokenExpired;
    }

    /**
     * 从请求头中获取Token
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authentication");
        if(token == null) {
            token = request.getHeader("token");
        } else {
            token = token.substring(SecurityConstants.TOKEN_SUFFIX.length());
        }
        if("".equals(token)) {
            token = null;
        }
        return token;
    }

    /**
     * 验证Token
     * @param token
     * @param username
     * @return
     */
    public static boolean validateToken(String token,String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return (usernameFromToken.equals(username) && isTokenExpired(token));
    }
}
