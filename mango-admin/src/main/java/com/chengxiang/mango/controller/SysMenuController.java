package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysMenu;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.service.SysMenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/23 17:25
 */
@RestController
@RequestMapping("/menu")
@Api(tags = {"菜单接口"})
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysMenu record) {
        return HttpResult.ok("0:新增失败 1:新增成功",sysMenuService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysMenu> records) {
        return HttpResult.ok("0:删除失败 1:新增成功",sysMenuService.delete(records));
    }

    @GetMapping("/findNavTree")
    public HttpResult findNavTree(@RequestParam String username) {
        return HttpResult.ok("查询成功",sysMenuService.findTree(username,1));
    }

    @GetMapping("/findMenuTree")
    public HttpResult findMenuTree() {
        return HttpResult.ok("查询成功",sysMenuService.findTree(null,0));
    }

}
