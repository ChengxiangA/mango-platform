package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysDict;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysDictService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("dict")
@Api(tags = {"字典接口"})
public class SysDictController {
    @Autowired
    private SysDictService sysDictService;

    @PostMapping("/save")
    public int save(@RequestBody SysDict sysDict) {
        return sysDictService.save(sysDict);
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysDict> records) {
        sysDictService.delete(records);
        return HttpResult.ok();
    }

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        PageResult page = sysDictService.findPage(pageRequest);
        return HttpResult.ok("成功匹配",page);
    }

    @GetMapping("/findByLabel")
    public HttpResult findByLabel(@RequestParam("label") String label) {
        return HttpResult.ok("成功匹配" + label + "字典",sysDictService.findByLabel(label));
    }
}
