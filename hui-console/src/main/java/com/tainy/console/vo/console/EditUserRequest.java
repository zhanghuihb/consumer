package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/8
 */
public class EditUserRequest implements Serializable{

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(value = "ID", required = true)
    private Integer id;

    @NotNull(message = "用户名称不能为空")
    @ApiModelProperty(value = "用户名称", required = true)
    private String username;

    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色", required = true)
    private String roleIds;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态", required = true)
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
