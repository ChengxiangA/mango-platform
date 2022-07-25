package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.entity.SysRole;
import com.chengxiang.mango.entity.SysRoleMenu;

import java.util.List;

public interface SysRoleService extends CrudService<SysRole> {

    /**
     * 查找所有角色
     * @return
     */
    List<SysRole> findAll();

    /**
     * 查询角色菜单集合
     * @param roleId
     * @return
     */
    List<SysMenu> findRoleMenus(Long roleId);

    /**
     * 保存角色菜单
     * @param records
     * @return
     */
    int saveRoleMenus(List<SysRoleMenu> records);

    /**
     * 根据姓名查询角色
     * @param name
     * @return
     */
    List<SysRole> findByName(String name);
}
