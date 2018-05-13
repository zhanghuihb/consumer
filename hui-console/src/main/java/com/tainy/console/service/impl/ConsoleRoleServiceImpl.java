package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.page.Page;
import com.tainy.console.mapper.ConsoleResourceMapper;
import com.tainy.console.mapper.ConsoleRoleMapper;
import com.tainy.console.service.ConsoleRoleService;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/6
 */
@Service
public class ConsoleRoleServiceImpl implements ConsoleRoleService {

    @Autowired
    private ConsoleRoleMapper consoleRoleMapper;

    @Autowired
    private ConsoleResourceMapper consoleResourceMapper;

    @Override
    public List<ConsoleRole> getAllRoles(ConsoleRole role) {
        return consoleRoleMapper.queryByObject(role);
    }

    @Override
    public List<ConsoleRole> selectListPageGetRoles(Page<?> page, ConsoleRole role) {
        List<ConsoleRole> resultList = consoleRoleMapper.selectListPageGetRoles(page, role);
        if(!CollectionUtils.isEmpty(resultList)){
            List<ConsoleResource> resourceList = null;
            for(ConsoleRole r : resultList){
                resourceList = new ArrayList<>();
                if(!StringUtil.isEmpty(r.getResourceIds())){
                    String[] resourcesIds = r.getResourceIds().split(",");
                    resourceList = consoleResourceMapper.queryResourcesByResourcesIds(resourcesIds);
                }
                r.setResourcesList(resourceList);
            }
        }
        return resultList;
    }

    @Override
    public boolean addRole(ConsoleRole role) {
        if(role == null){
            return false;
        }

        int i = consoleRoleMapper.insertSelective(role);

        return i > 0 ? true : false;
    }

    @Override
    public boolean editRole(ConsoleRole role) {
        if(role == null || role.getId() == null){
            return false;
        }

        int i = consoleRoleMapper.updateByPrimaryKeySelective(role);

        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteRole(Integer id) {
        int i = consoleRoleMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }
}
