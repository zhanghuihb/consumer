package com.tainy.common.domain.console;

import com.tainy.common.domain.BaseBean;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ConsoleUser extends BaseBean{

    private String username;

    private String password;

    private String salt;

    private String roleIds;

    private String roleNames;

    private Integer status;

    private List<ConsoleRole> roleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds == null ? null : roleIds.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleNames() {
        roleNames = "";
        if(!CollectionUtils.isEmpty(roleList)){
            for (ConsoleRole role : roleList) {
                roleNames = roleNames + "," + role.getRole();
            }
            roleNames = roleNames.substring(1);
        }
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public List<ConsoleRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ConsoleRole> roleList) {
        this.roleList = roleList;
    }
}