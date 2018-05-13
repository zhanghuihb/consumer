package com.tainy.console.vo.console;

import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public class EditRoleRequest implements Serializable {

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    @NotNull(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String role;

    @NotNull(message = "描述不能为空")
    @ApiModelProperty(value = "描述", required = true)
    private String description;

    @NotNull(message = "U权限不能为空")
    @ApiModelProperty(value = "权限", required = true)
    private String resourceIds;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态", required = true)
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
