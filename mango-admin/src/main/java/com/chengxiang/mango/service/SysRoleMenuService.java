package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> findAll();
}
