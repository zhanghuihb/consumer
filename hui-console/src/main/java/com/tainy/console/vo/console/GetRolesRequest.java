package com.tainy.console.vo.console;

import com.tainy.common.util.vo.page.PageRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public class GetRolesRequest implements Serializable{

    @ApiModelProperty(value = "查询角色名", required = true)
    private String queryRole;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    public String getQueryRole() {
        return queryRole;
    }

    public void setQueryRole(String queryRole) {
        this.queryRole = queryRole;
    }

    public PageRequest.Page getPage() {
        return page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }
}
