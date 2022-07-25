package com.chengxiang.mango.mapper;

import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRoleMenu record);

    int insertSelective(SysRoleMenu record);

    SysRoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRoleMenu record);

    int updateByPrimaryKey(SysRoleMenu record);

    List<SysRoleMenu> findAll();

    List<SysMenu> findRoleMenus(@Param("roleId") Long roleId);

    int deleteByRoleId(@Param("roleId") Long roleId);
}