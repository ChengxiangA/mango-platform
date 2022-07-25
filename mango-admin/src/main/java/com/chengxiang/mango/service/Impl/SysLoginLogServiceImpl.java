package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysLog;
import com.chengxiang.mango.entity.SysLoginLog;
import com.chengxiang.mango.mapper.SysLoginLogMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/19 14:39
 */
@Service
public class SysLoginLogServiceImpl implements SysLoginLogService {

    @Autowired
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int writeLoginLog(String username, String ip) {
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setIp(ip);
        sysLoginLog.setUserName(username);
        sysLoginLog.setCreateTime(LocalDateTime.now());
        sysLoginLog.setStatus(SysLoginLog.STATUS_LOGIN);
        return sysLoginLogMapper.insertSelective(sysLoginLog);
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
            return MybatisPageHelper.findPage(pageRequest,sysLoginLogMapper,"findPageByUsernameAndStatus",username,status);
        } else if(username != null) {
            return MybatisPageHelper.findPage(pageRequest,sysLoginLogMapper,"findPageByUsername",username);
        } else if(status != null) {
            return MybatisPageHelper.findPage(pageRequest,sysLoginLogMapper,"findPageByStatus",status);
        }
        // 没有条件，查询全部日志.
        return MybatisPageHelper.findPage(pageRequest,sysLoginLogMapper);
    }


}
