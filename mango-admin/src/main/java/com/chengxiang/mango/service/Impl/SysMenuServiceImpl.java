package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.constant.SysConstants;
import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.mapper.SysMenuMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public int save(SysMenu record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysMenuMapper.insertSelective(record);
        }
        if(record.getParentId() == null) {
            record.setParentId(0L);
        }
        return sysMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysMenu record) {
        return sysMenuMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysMenu> records) {
        for(SysMenu record:records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysMenu findById(Long id) {
        return sysMenuMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysMenuMapper);
    }

    @Override
    public List<SysMenu> findTree(String username,int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        List<SysMenu> menus = findByUser(username);
        for (SysMenu menu : menus) {
            // 先找到一级目录
            if(menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                sysMenus.add(menu);
            }
        }
        findChildren(sysMenus,menus,menuType);
        return sysMenus;
    }


    private void findChildren(List<SysMenu> sysMenus,List<SysMenu> menus,int menuType) {
        for(SysMenu sysMenu: sysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                // 如果不需要获取按钮，则跳过
                if(menuType == 1 && menu.getType() == 2) {
                    continue;
                }
                if(menu.getParentId() != null && menu.getParentId() == sysMenu.getId()) {
                    menu.setLevel(sysMenu.getLevel() + 1);
                    menu.setParentName(sysMenu.getName());
                    children.add(menu);
                }
            }
            if(menuType == 0) {
                findChildren(children,menus,menuType);
            }
            sysMenu.setChildren(children);
        }
    }


    /**
     * 根据用户名查找菜单列表
     * @param username
     * @return
     */
    @Override
    public List<SysMenu> findByUser(String username) {
        if(StringUtils.isEmpty(username) || SysConstants.ADMIN.equals(username)) {
            return sysMenuMapper.findAll();
        }
        return sysMenuMapper.findByUsername(username);
    }


}
