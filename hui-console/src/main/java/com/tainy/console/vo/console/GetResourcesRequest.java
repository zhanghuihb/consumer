package com.tainy.console.vo.console;

import com.tainy.common.util.vo.page.PageRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public class GetResourcesRequest implements Serializable{

    @ApiModelProperty(value = "查询权限名", required = true)
    private String queryName;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public PageRequest.Page getPage() {
        return page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }
}
