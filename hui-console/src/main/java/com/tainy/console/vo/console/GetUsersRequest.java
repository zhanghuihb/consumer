package com.tainy.console.vo.console;

import com.tainy.common.util.vo.page.PageRequest;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/8
 */
public class GetUsersRequest implements Serializable{

    @ApiModelProperty(value = "查询用户名", required = true)
    private String queryUsername;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    public String getQueryUsername() {
        return queryUsername;
    }

    public void setQueryUsername(String queryUsername) {
        this.queryUsername = queryUsername;
    }

    public PageRequest.Page getPage() {
        return page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }
}
