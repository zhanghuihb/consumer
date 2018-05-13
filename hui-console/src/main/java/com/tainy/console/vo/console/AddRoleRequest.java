package com.tainy.console.vo.console;

import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public class AddRoleRequest implements Serializable {

    @NotNull(message = "权限名称不能为空")
    @ApiModelProperty(value = "权限名称", required = true)
    private String role;

    @NotNull(message = "权限类型不能为空")
    @ApiModelProperty(value = "权限类型", required = true)
    private String description;

    @NotNull(message = "URL不能为空")
    @ApiModelProperty(value = "URL", required = true)
    private String resourceIds;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }
}
