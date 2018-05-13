package com.tainy.service;

import com.tainy.entity.Organization;

import java.util.List;

/**
 * Organization服务层接口
 * @author Tainy
 * @date 2017/11/18
 */
public interface OrganizationService {
    /**
     * 查询组织机构
     *
     * @return
     */
    List<Organization> listOrganization();
}
