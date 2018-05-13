package com.tainy.common.domain.console;

import com.tainy.common.domain.BaseBean;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class ConsoleRole extends BaseBean{

    private String role;

    private String description;

    private String resourceIds;

    private String resourceNames;

    private Integer status;

    private List<ConsoleResource> resourcesList;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds == null ? null : resourceIds.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ConsoleResource> getResourcesList() {
        return resourcesList;
    }

    public void setResourcesList(List<ConsoleResource> resourcesList) {
        this.resourcesList = resourcesList;
    }

    public String getResourceNames() {
        resourceNames = "";
        if(!CollectionUtils.isEmpty(resourcesList)){
            for (ConsoleResource res : resourcesList) {
                resourceNames = resourceNames + "," + res.getName();
            }
            resourceNames = resourceNames.substring(1);
        }
        return resourceNames;
    }

    public void setResourceNames(String resourceNames) {
        this.resourceNames = resourceNames;
    }
}