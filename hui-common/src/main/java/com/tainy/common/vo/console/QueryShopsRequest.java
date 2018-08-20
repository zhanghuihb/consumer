package com.tainy.common.vo.console;

import com.tainy.common.page.PageParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Tainy
 * @date 2018/8/14 13:34
 */
@Data
public class QueryShopsRequest extends PageParam{

    @ApiModelProperty(value = "商户名称", required = true)
    private String shopName;

    @ApiModelProperty(value = "经度", required = true)
    private Double longitude;

    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude;
}
