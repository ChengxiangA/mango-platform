package com.chengxiang.mango.mapper;

import com.chengxiang.mango.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);

    List<SysMenu> findPage();
    
    List<SysMenu> findPageByName(@Param("name") String name);

    List<SysMenu> findAll();

    /**
     * 根据用户名查找用户对应的权限
     * @param username
     * @return
     */
    List<SysMenu> findByUsername(@Param("username") String username);

    List<SysMenu> findRoleMenus(@Param("roleId") Long roleId);
}