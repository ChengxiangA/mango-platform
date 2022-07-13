package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysConfig;
import com.chengxiang.mango.entity.SysDept;

import java.util.List;

public interface SysDeptService {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysConfig> findAll();
}
