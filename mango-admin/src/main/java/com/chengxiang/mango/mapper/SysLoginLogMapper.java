package com.chengxiang.mango.mapper;

import com.chengxiang.mango.entity.SysLoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLoginLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLoginLog record);

    int insertSelective(SysLoginLog record);

    SysLoginLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLoginLog record);

    int updateByPrimaryKey(SysLoginLog record);

    List<SysLoginLog> findPage();

    List<SysLoginLog> findPageByUsername(@Param("username") String username);

    List<SysLoginLog> findPageByStatus(@Param("status") String status);

    List<SysLoginLog> findPageByUsernameAndStatus(@Param("username") String username,@Param("status") String status);

    List<SysLoginLog> findByUsernameAndStatus(@Param("username") String username,@Param("status") String status);
}