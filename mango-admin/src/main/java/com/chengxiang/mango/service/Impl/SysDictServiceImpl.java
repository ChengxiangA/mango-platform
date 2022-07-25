package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysDict;
import com.chengxiang.mango.mapper.SysDictMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysDictService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDictServiceImpl implements SysDictService {
    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public int save(SysDict record) {
        return sysDictMapper.insert(record);
    }

    @Override
    public int delete(SysDict record) {
        return sysDictMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDict> records) {
        for (SysDict record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDict findById(Long id) {
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
     * 支持label条件的分页查询
     * 判断请求体中是否有label字段
     * 如果有则对其进行含有改label字段值的模糊查询，即%#{label}%
     * @param pageRequest
     * @return
     */
    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParamValue("label");
        if(label == null) {
            return MybatisPageHelper.findPage(pageRequest,sysDictMapper);
        } else {
            return MybatisPageHelper.findPage(pageRequest,sysDictMapper,"findByLabel",label);
        }
    }

    /**
     * 对label的精准查询
     * @param label
     * @return
     */
    @Override
    public List<SysDict> findByLabel(String label) {
        return sysDictMapper.findByLabel(label);
    }
}
