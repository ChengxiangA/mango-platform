package com.chengxiang.mango.page;

import java.util.List;

/**
 * 分页返回结果
 */
public class PageResult<T> {
    // 当前页码
    private int pageNum;
    // 页面大小
    private int pageSize;
    // 总数据条数
    private long totalSize;
    // 总页码
    private int totalPage;
    // 分页数据
    private List<T> content;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
