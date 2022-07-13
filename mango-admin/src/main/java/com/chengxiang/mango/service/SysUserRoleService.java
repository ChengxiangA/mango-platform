package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);

    List<SysUserRole> findAll();
}
