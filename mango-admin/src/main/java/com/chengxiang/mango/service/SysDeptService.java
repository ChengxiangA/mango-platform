package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysConfig;
import com.chengxiang.mango.entity.SysDept;

import java.util.List;

public interface SysDeptService extends CrudService<SysDept> {
    /**
     * 查询机构树
     * @return
     */
    List<SysDept> findTree();
}
