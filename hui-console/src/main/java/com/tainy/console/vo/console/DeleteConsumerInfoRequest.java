package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/14
 */
public class DeleteConsumerInfoRequest implements Serializable{

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
