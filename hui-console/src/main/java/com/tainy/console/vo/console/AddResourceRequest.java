package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public class AddResourceRequest implements Serializable {

    @NotNull(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String name;

    @NotNull(message = "权限类型不能为空")
    @ApiModelProperty(value = "权限类型", required = true)
    private String type;

    @NotNull(message = "URL不能为空")
    @ApiModelProperty(value = "URL", required = true)
    private String url;

    @NotNull(message = "权限路径不能为空")
    @ApiModelProperty(value = "权限路径", required = true)
    private String permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
