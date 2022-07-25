package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysLog;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.service.SysLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 操作日志控制器
 * @author 程祥
 * @date 2022/7/21 9:07
 */
@RestController
@RequestMapping("/log")
@Api(tags = {"操作日志接口"})
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok("查找操作日志成功",sysLogService.findPage(pageRequest));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysLog> records) {
        return HttpResult.ok("删除成功",sysLogService.delete(records));
    }
}
