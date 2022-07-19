package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysLoginLog;
import com.chengxiang.mango.mapper.SysLoginLogMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/19 14:39
 */
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int writeLoginLog(String userName, String ip) {
        return 0;
    }

    @Override
    public int save(SysLoginLog record) {
        if(record.getId() == null || record.getId() == 0) {
            return sysLoginLogMapper.insertSelective(record);
        }
        return sysLoginLogMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysLoginLog record) {
        return sysLoginLogMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysLoginLog> records) {
        for (SysLoginLog record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysLoginLog findById(Long id) {
        return sysLoginLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object username = pageRequest.getParamValue("username");
        Object status = pageRequest.getParamValue("status");
        if(username != null && status != null) {
            return MybatisPageHelper.findPage(pageRequest,SysLoginLogMapper.class,"findPageByUsernameAndStatus",username,status);
        } else if(username != null) {
            return MybatisPageHelper.findPage(pageRequest,SysLoginLogMapper.class,"findPageByStatus",status);
        } else if(status != null) {
            return MybatisPageHelper.findPage(pageRequest,SysLoginLogMapper.class,"findPageByUsername",username);
        }
        // 没有条件，查询全部日志.
        return MybatisPageHelper.findPage(pageRequest,SysLoginLogMapper.class);
    }
}