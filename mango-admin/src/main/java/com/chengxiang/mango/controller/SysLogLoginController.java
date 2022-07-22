package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysLoginLog;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.mapper.SysLoginLogMapper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录日志控制器
 * @author 程祥
 * @date 2022/7/20 16:04
 */
@RestController
@RequestMapping(value = "/loginlog")
public class SysLogLoginController {
    @Autowired
    SysLoginLogService sysLoginLogService;

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        PageResult page = sysLoginLogService.findPage(pageRequest);
        return HttpResult.ok("查询日志成功",page);
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysLoginLog> records) {
        return HttpResult.ok("删除成功", sysLoginLogService.delete(records));
    }
}
