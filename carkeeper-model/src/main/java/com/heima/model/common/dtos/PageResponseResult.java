package com.heima.model.common.dtos;

import java.io.Serializable;

public class PageResponseResult extends ResponseResult implements Serializable {
    private Integer currentPage;
    private Integer size;
    private Integer total;

    public PageResponseResult(Integer currentPage, Integer size, Integer total,Object data) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
        this.setData(data);
    }

    public PageResponseResult(Integer currentPage, Integer size, Integer total) {
        this.currentPage = currentPage;
        this.size = size;
        this.total = total;
    }
    /**
     * 封装分页结果
     * @param currentPage 当前页码
     * @param size 页大小
     * @param total 总记录数
     * @param data 结果集
     * @return
     */
    public static ResponseResult page(Integer currentPage, Integer size, Integer total,Object data) {
        PageResponseResult result = new PageResponseResult(currentPage, size, total, data);
        return result;
    }

    public PageResponseResult() {

    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
