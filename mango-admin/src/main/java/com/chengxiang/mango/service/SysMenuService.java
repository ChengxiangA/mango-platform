package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends CrudService<SysMenu> {
    List<SysMenu> findTree();

    /**
     * 根据用户名查找菜单列表
     * @param username
     * @return
     */
    List<SysMenu> findByUser(String username);
}
