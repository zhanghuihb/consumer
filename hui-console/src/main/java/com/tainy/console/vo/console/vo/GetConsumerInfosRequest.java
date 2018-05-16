package com.tainy.console.vo.console.vo;

import com.tainy.common.util.vo.page.PageRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/14
 */
public class GetConsumerInfosRequest implements Serializable{

    @ApiModelProperty(value = "查询消费记录状态", required = true)
    private Integer queryStatus;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    public Integer getQueryStatus() {
        return queryStatus;
    }

    public void setQueryStatus(Integer queryStatus) {
        this.queryStatus = queryStatus;
    }

    public PageRequest.Page getPage() {
        return page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }
}
