package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysConfig;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysConfigService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/25 10:59
 */
@RestController
@RequestMapping("/config")
@Api(tags = {"配置接口"})
public class SysConfigController {
    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysConfig record) {
        return HttpResult.ok("0:新增失败 1:新增成功",sysConfigService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysConfig> records) {
        return HttpResult.ok("0:删除失败 1:删除成功",sysConfigService.delete(records));
    }

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        PageResult page = sysConfigService.findPage(pageRequest);
        return HttpResult.ok("查找成功",page);
    }

    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam("label") String label) {
        return HttpResult.ok("查询成功",sysConfigService.findByLabel(label));
    }

    @GetMapping("/findAll")
    public HttpResult findAll() {
        return HttpResult.ok("查询成功",sysConfigService.findAll());
    }
}
