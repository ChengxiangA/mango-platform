package com.chengxiang.mango.controller;

import com.chengxiang.mango.constant.SysConstants;
import com.chengxiang.mango.entity.SysUser;
import com.chengxiang.mango.http.HttpResult;
import com.chengxiang.mango.mapper.SysUserMapper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysUserService;
import com.chengxiang.mango.util.PasswordUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = {"用户接口"})
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public HttpResult save(@ApiParam("用户对象") @RequestBody SysUser record) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        SysUser sysUserByName = sysUserService.findByName(record.getName());
        SysUser sysUserById = sysUserService.findById(record.getId());
        if(sysUserByName == null && sysUserById == null) {
            String salt = PasswordUtil.getRandomSalt();
            String encPass = PasswordUtil.encode(salt, record.getPassword());
            record.setSalt(salt);
            record.setPassword(encPass);
            return HttpResult.ok("添加成功",sysUserService.save(record));
        } else {
            return HttpResult.error("添加失败，用户已存在！");
        }
    }

    /**
     * 用户名和ID均为不可修改项，所以作为查找项
     * 超级管理员ADMIN不支持修改
     * @param record
     * @return
     */
    @ApiOperation("更新用户")
    @PostMapping("/update")
    public HttpResult update(@ApiParam("用户对象") @RequestBody SysUser record) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        SysUser sysUserByName = sysUserService.findByName(record.getName());
        SysUser sysUserById = sysUserService.findById(record.getId());
        if(sysUserByName != null && sysUserById != null && !sysUserById.getName().equals(sysUserByName.getName())) {
            return HttpResult.error("ID和姓名不是同一人，无法确定用户");
        } else if(sysUserById == null && sysUserByName == null) {
            return HttpResult.error("用户不存在");
        } else {
            if(SysConstants.ADMIN.equals(record.getName())) {
                return HttpResult.error("超级管理员不支持修改");
            } else {
                // 如果改了密码的话，必须重新加密,我这里直接使用之前生成的盐值，也可以重新生成
                if(record.getPassword() != sysUserByName.getPassword()) {
                    record.setPassword(PasswordUtil.encode(sysUserByName.getSalt(),record.getPassword()));
                }
                return HttpResult.ok("更新成功",sysUserService.update(record));
            }
        }
    }

    @ApiOperation("分页查找用户")
    @PostMapping("/findPage")
    public HttpResult findPage(@ApiParam("分页对象") @RequestBody PageRequest pageRequest) {
        return HttpResult.ok("用户信息获取成功",sysUserService.findPage(pageRequest));
    }

    /**
     * 只支持批量删除
     * @param records
     * @return
     */
    @ApiOperation("删除用户")
    @PostMapping("/delete")
    public HttpResult delete(@ApiParam("删除用户列表") @RequestBody List<SysUser> records) {
        if(sysUserService.delete(records) == 0) {
            return HttpResult.error("超级管理员不允许删除!");
        }
        return HttpResult.ok("成功删除",sysUserService.delete(records));
    }

    @ApiOperation("根据用户名查找用户")
    @GetMapping("/findByName")
    public HttpResult findByName(@ApiParam("用户名") @RequestParam("username") String username) {
        return HttpResult.ok("查询成功！",sysUserService.findByName(username));
    }

    @ApiOperation("分页导出用户Excel文件")
    @PostMapping("/exportUserExcelFile")
    public void exportUserExcelFile(@ApiParam("分页对象") @RequestBody PageRequest pageRequest,@ApiParam("响应") HttpServletResponse response) throws IOException {
        sysUserService.createUserExcelFile(pageRequest,response);
    }

    @ApiOperation("根据用户名查找用户权限")
    @PostMapping("/findPermissions")
    public HttpResult findPermissions(@ApiParam("用户名") @RequestParam("username") String username) {
        return HttpResult.ok("权限查询成功",sysUserService.findPermissions(username));
    }
}
