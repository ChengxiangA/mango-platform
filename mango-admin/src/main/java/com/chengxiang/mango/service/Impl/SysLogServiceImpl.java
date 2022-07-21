package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysLog;
import com.chengxiang.mango.mapper.SysLogMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysLogService;
import com.chengxiang.mango.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/20 21:00
 */
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int save(SysLog record) {
        if(record == null || record.getId() == 0) {
            return sysLogMapper.insertSelective(record);
        }
        return sysLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLog record) {
        return sysLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLog> records) {
        for (SysLog record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysLog findById(Long id) {
        return sysLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object username = pageRequest.getParamValue("username");
        if(username != null) {
            return MybatisPageHelper.findPage(pageRequest,sysLogMapper,"findPageByUsername",username);
        }
        return MybatisPageHelper.findPage(pageRequest,sysLogMapper);
    }
}
