package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysLoginLog;

import java.util.List;

public interface SysLoginLogService {
    int deleteByPrimaryKey(Long id);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);

    List<SysLoginLog> findAll();
}
