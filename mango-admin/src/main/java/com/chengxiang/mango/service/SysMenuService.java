package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends CrudService<SysMenu> {

    /**
     * 查询菜单树
     * @param username
     * @param menuType 获取菜单类型 0：获取所有菜单，包含按钮， 1：获取所有菜单，不包含按钮
     * @return
     */
    List<SysMenu> findTree(String username,int menuType);

    /**
     * 根据用户名查找菜单列表
     * @param username
     * @return
     */
    List<SysMenu> findByUsername(String username);

}
