package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 查找所有角色
     * @return
     */
    List<SysRole> findAll();
}
