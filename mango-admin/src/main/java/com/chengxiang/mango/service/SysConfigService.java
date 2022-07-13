package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SysConfigService {
    int deleteByPrimaryKey(Long id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);

    List<SysConfig> findAll();
}
