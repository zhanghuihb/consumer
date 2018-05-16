package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/2/21
 */
public class StatisticsConsumerDataRequest implements Serializable{

    @NotNull(message = "查询分类不能为空")
    @ApiModelProperty(value = "查询分类", required = true)
    private Integer category;

    @ApiModelProperty(value = "查询开始时间", required = true)
    private String queryTimeStart;

    @ApiModelProperty(value = "查询结束时间", required = true)
    private String queryTimeEnd;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getQueryTimeStart() {
        return queryTimeStart;
    }

    public void setQueryTimeStart(String queryTimeStart) {
        this.queryTimeStart = queryTimeStart;
    }

    public String getQueryTimeEnd() {
        return queryTimeEnd;
    }

    public void setQueryTimeEnd(String queryTimeEnd) {
        this.queryTimeEnd = queryTimeEnd;
    }
}
