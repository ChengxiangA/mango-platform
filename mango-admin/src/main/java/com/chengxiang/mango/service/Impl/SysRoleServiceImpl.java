package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.entity.SysRole;
import com.chengxiang.mango.entity.SysRoleMenu;
import com.chengxiang.mango.mapper.SysRoleMapper;
import com.chengxiang.mango.mapper.SysRoleMenuMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/25 11:32
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int save(SysRole record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysRoleMapper.insertSelective(record);
        }
        return sysRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysRole record) {
        return sysRoleMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysRole> records) {
        for (SysRole record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("name");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest,sysRoleMapper,"findPageByName",label);
        }
        return MybatisPageHelper.findPage(pageRequest,sysRoleMapper);
    }

    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }

    @Override
    public List<SysMenu> findRoleMenus(Long roleId) {
        return sysRoleMenuMapper.findRoleMenus(roleId);
    }

    @Transactional
    @Override
    public int saveRoleMenus(List<SysRoleMenu> records) {
        Long roleId = records.get(0).getRoleId();
        SysRole sysRole = sysRoleMapper.selectByPrimaryKey(roleId);
        if(sysRole == null || records == null || records.isEmpty()) {
            return 0;
        }
        sysRoleMenuMapper.deleteByRoleId(roleId);
        for (SysRoleMenu record : records) {
            sysRoleMenuMapper.insertSelective(record);
        }
        return 1;
    }

    @Override
    public List<SysRole> findByName(String name) {
        return sysRoleMapper.findByName(name);
    }
}
