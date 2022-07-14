package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysUser;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface SysUserService extends CrudService<SysUser> {

    int update(SysUser sysUser);
    List<SysUser> findAll();

    PageResult findPage(PageRequest pageRequest);

    SysUser findByName(@Param("name") String name);

    void createUserExcelFile(PageRequest pageRequest,HttpServletResponse response) throws IOException;

    Set<String> findPermissions(String userName);
}
