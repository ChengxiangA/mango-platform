package com.chengxiang.mango.controller;

import com.chengxiang.mango.entity.SysDept;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/21 17:30
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController {
    @Autowired
    private SysDeptService sysDeptService;

    @PostMapping("/save")
    public HttpResult save(@RequestBody SysDept record) {
        return HttpResult.ok("0:插入失败 1:插入成功",sysDeptService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<SysDept> records) {
        return HttpResult.ok("0:删除失败 1:删除成功",sysDeptService.delete(records));
    }

    @GetMapping("/findTree")
    public HttpResult findTree() {
        return HttpResult.ok("查找成功",sysDeptService.findTree());
    }
}
