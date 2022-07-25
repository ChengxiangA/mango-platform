package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SysConfigService extends CrudService<SysConfig> {
    List<SysConfig> findAll();

    List<SysConfig> findByLabel(String label);
}
