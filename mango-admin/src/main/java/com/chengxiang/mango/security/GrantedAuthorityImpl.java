package com.chengxiang.mango.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 * @author 程祥
 * @date 2022/7/13 16:49
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
