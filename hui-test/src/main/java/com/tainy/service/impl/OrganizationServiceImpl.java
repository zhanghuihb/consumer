package com.tainy.service.impl;

import com.tainy.entity.Organization;
import com.tainy.mapper.OrganizationMapper;
import com.tainy.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tainy
 * @date 2017/11/18
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> listOrganization() {
        return organizationMapper.listOrganization();
    }
}
