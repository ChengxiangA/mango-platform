package com.chengxiang.mango.service;

import com.chengxiang.mango.page.PageRequest;
import com.chengxiang.mango.page.PageResult;

import java.util.List;

public interface CrudService<T> {
    int save(T record);

    int delete(T record);

    int delete(List<T> records);

    T findById(Long id);

    /**
     * 分页封装
     * @param pageRequest
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
