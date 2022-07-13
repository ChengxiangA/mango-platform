package com.chengxiang.mango.mapper;

import com.chengxiang.mango.entity.SysDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDictMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);

    List<SysDict> findAll();

    List<SysDict> findPage();

    List<SysDict> findPageByLabel(@Param("label") String label);

    List<SysDict> findByLabel(@Param("label") String label);
}