package com.chengxiang.mango.service;

import com.chengxiang.mango.entity.SysLoginLog;

import java.util.List;

public interface SysLoginLogService extends CrudService<SysLoginLog> {
    /**
     * 记录登录日志
     * @param userName
     * @param ip
     * @return
     */
    int writeLoginLog(String userName,String ip);
}
