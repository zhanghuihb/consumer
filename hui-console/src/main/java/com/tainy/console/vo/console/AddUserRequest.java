package com.tainy.console.vo.console;

import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2017/12/22
 */
public class AddUserRequest implements Serializable{

    @NotNull(message = "登录名不能为空")
    @ApiModelProperty(value = "登录名", required = true)
    private String username;

    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "用户角色不能为空")
    @ApiModelProperty(value = "用户角色", required = true)
    private String roleIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
