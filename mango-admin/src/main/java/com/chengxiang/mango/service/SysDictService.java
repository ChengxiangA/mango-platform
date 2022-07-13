package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysDict;

import java.util.List;

public interface SysDictService extends CrudService<SysDict> {
    /**
     * 根据label准确查询
     * @param label
     * @return
     */
    List<SysDict> findByLabel(String label);
}
