package com.tainy.common.vo.console;

import com.tainy.common.page.PageParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/8/14 13:34
 */
@Data
public class QueryShopsRequest extends PageParam{

    private String shopName;

    private String address;

    private String province;

    private Double longitude;

    private Double latitude;
}
