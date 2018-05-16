package com.tainy.console.vo.console;

import com.tainy.common.util.vo.page.PageRequest;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/12
 */
public class GetHotelsRequest implements Serializable{

    @ApiModelProperty(value = "查询酒店名", required = true)
    private String queryName;

    @ApiModelProperty(value = "查询城市", required = true)
    private String queryCity;

    @ApiModelProperty(value = "查询酒店地址", required = true)
    private String queryAddress;

    @ApiModelProperty(value = "分页参数", required = true)
    private PageRequest.Page page;

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String getQueryCity() {
        return queryCity;
    }

    public void setQueryCity(String queryCity) {
        this.queryCity = queryCity;
    }

    public String getQueryAddress() {
        return queryAddress;
    }

    public void setQueryAddress(String queryAddress) {
        this.queryAddress = queryAddress;
    }

    public PageRequest.Page getPage() {
        return page;
    }

    public void setPage(PageRequest.Page page) {
        this.page = page;
    }
}
