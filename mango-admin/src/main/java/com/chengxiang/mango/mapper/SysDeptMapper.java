package com.chengxiang.mango.mapper;

import com.chengxiang.mango.entity.SysConfig;
import com.chengxiang.mango.entity.SysDept;

import java.util.List;

public interface SysDeptMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    SysDept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    List<SysDept> findAll();

    List<SysDept> findPage();
}