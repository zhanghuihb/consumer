package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.Organization;

import java.util.List;

public interface OrganizationMapper extends BaseMapper<Organization>{

    List<Organization> listOrganization();

}