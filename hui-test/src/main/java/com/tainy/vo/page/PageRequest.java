package com.tainy.vo.page;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2017/11/10
 */
public class PageRequest implements Serializable{
    protected Page page;// 分页参数

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public static class Page implements Serializable {

        protected int pageSize = 20; // 每页显示记录数
        protected int currentPage; // 当前页, 从1开始
        protected int currentResult; // 当前记录起始索引, 从0开始
        protected int totalPage; // 总页数
        protected int totalResult; // 总记录数

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getCurrentResult() {
            return currentResult;
        }

        public void setCurrentResult(int currentResult) {
            this.currentResult = currentResult;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalResult() {
            return totalResult;
        }

        public void setTotalResult(int totalResult) {
            this.totalResult = totalResult;
        }
    }
}
