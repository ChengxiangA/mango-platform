package com.chengxiang.mango.service.Impl;

import com.chengxiang.mango.entity.SysDept;
import com.chengxiang.mango.mapper.SysDeptMapper;
import com.chengxiang.mango.page.MybatisPageHelper;
import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;
import com.chengxiang.mango.service.SysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 程祥
 * @date 2022/7/21 9:16
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;


    @Override
    public int save(SysDept record) {
        if(record == null || record.getId() == 0) {
            return sysDeptMapper.insertSelective(record);
        }
        return sysDeptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int delete(SysDept record) {
        return sysDeptMapper.deleteByPrimaryKey(record.getId());
    }

    @Override
    public int delete(List<SysDept> records) {
        for (SysDept record : records) {
            delete(record);
        }
        return 1;
    }

    @Override
    public SysDept findById(Long id) {
        return sysDeptMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,sysDeptMapper);
    }

    @Override
    public List<SysDept> findTree() {
        List<SysDept> fatherDepts = new ArrayList<>();
        List<SysDept> depts = sysDeptMapper.findAll();
        for (SysDept dept : depts) {
            if(dept.getParentId() == null || dept.getParentId() == 0) {
                dept.setLevel(0);
                fatherDepts.add(dept);
            }
        }
        findChildren(fatherDepts,depts);
        return fatherDepts;
    }

    private void findChildren(List<SysDept> fatherDepts,List<SysDept> depts) {
        for (SysDept fatherDept : fatherDepts) {
            List<SysDept> child = new ArrayList<>();
            for (SysDept dept : depts) {
                if(fatherDept.getId().equals(dept.getParentId())) {
                    dept.setParentName(fatherDept.getName());
                    dept.setLevel(fatherDept.getLevel() + 1);
                    child.add(dept);
                }
            }
            fatherDept.setChildren(child);
            findChildren(child,depts);
        }
        return;
    }
}
