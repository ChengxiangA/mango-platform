package com.chengxiang.mango.page;


import com.chengxiang.mango.common.utils.ReflectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;

public class MybatisPageHelper {

    public static final String findPage = "findPage";

    /**
     * 分页查询，如果不填写方法名 则 约定查询方法为 ‘findPage’
     * @param pageRequest 分页请求
     * @param mapper Dao对象，Mybatis的Mapper
     * @return 封装的分页结果
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper) {
        return findPage(pageRequest,mapper,findPage);
    }

    /**
     * 传入分页请求对象，mapper类，方法名，方法参数
     * 通过反射调用分页方法
     * @param pageRequest
     * @param mapper
     * @param queryMethodName
     * @param args
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper,String queryMethodName,Object... args) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        Object result = ReflectionUtil.invoke(mapper,queryMethodName, args);
        return getPageResult(new PageInfo<>((List) result));
    }

    public static PageResult getPageResult(PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult<>();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPage(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }

}
