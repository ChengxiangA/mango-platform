package com.chengxiang.mango.security;

import com.chengxiang.mango.entity.SysUser;
import com.chengxiang.mango.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在!");
        }
        Set<String> permissions = sysUserService.findPermissions(username);
        List<GrantedAuthorityImpl> grantedAuthorities = new ArrayList<>();
        for (String permission : permissions) {
            grantedAuthorities.add(new GrantedAuthorityImpl(permission));
        }
        return new JwtUserDetails(username,user.getPassword(),user.getSalt(),grantedAuthorities);
    }
}
