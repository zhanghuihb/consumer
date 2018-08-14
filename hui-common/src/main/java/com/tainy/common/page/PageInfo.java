package com.tainy.common.page;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author Tainy
 * @date 2018/8/14 13:58
 */
@SuppressWarnings("unchecked")
public class PageInfo<Data> implements Serializable{

    private int currentPage;// 当前页

    private int pageSize;// 每页条数

    private int totalPage;// 总页数

    private long totalRecord;// 总记录数

    private Data data;// 结果集

    public static <Data>PageInfo<Data> newInstance(int currentPage, int pageSize){
        return new PageInfo<>(currentPage, pageSize);
    }

    public PageInfo(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageInfo() {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            Class<Data> clazz = (Class<Data>) parameterizedType.getActualTypeArguments()[0];
            data = clazz.newInstance();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
