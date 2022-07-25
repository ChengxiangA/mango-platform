package com.chengxiang.mango.controller;

import com.chengxiang.mango.constant.SysConstants;
import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.entity.SysRole;
import com.chengxiang.mango.entity.SysRoleMenu;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.mapper.SysRoleMapper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.service.SysRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/25 14:54
 */
@RestController
@RequestMapping("/role")
@Api(tags = {"角色接口"})
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysRole record) {
        record.setId(null);
        if(record.getName().equalsIgnoreCase(SysConstants.ADMIN)) {
            return HttpResult.error("超级管理员不允许修改");
        }
        return HttpResult.ok("0:保存失败 1:保存成功",sysRoleService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysRole> records) {
        return HttpResult.ok("0:删除失败 1:删除成功",sysRoleService.delete(records));
    }

    @GetMapping("/findByName")
    public HttpResult findByName(@RequestParam("name") String name) {
        return HttpResult.ok("查询成功",sysRoleService.findByName(name));
    }

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok("查询成功",sysRoleService.findPage(pageRequest));
    }
    
    @PostMapping("/findAll")
    public HttpResult findAll() {
        return HttpResult.ok("查询成功",sysRoleService.findAll());
    }

    @GetMapping("/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam("roleId") Long roleId) {
        List<SysMenu> roleMenus = sysRoleService.findRoleMenus(roleId);
        return HttpResult.ok("查询成功",roleMenus);
    }

    @PostMapping("/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<SysRoleMenu> records) {
        for (SysRoleMenu record : records) {
            SysRole sysRole = sysRoleMapper.selectByPrimaryKey(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        return HttpResult.ok("0:删除失败 1:删除成功",sysRoleService.saveRoleMenus(records));
    }
}
