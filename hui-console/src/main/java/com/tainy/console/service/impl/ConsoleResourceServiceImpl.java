package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.util.page.Page;
import com.tainy.console.mapper.ConsoleResourceMapper;
import com.tainy.console.service.ConsoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/6
 */
@Service
public class ConsoleResourceServiceImpl implements ConsoleResourceService {

    @Autowired
    private ConsoleResourceMapper consoleResourceMapper;

    @Override
    public List<ConsoleResource> getAllResources(ConsoleResource resource) {
        return consoleResourceMapper.queryByObject(resource);
    }

    @Override
    public List<ConsoleResource> selectListPageGetResources(Page<?> page, ConsoleResource resource) {
        return consoleResourceMapper.selectListPageGetResources(page, resource);
    }

    @Override
    public boolean addResource(ConsoleResource resource) {
        if(resource == null){
            return false;
        }

        int i = consoleResourceMapper.insertSelective(resource);

        return i > 0 ? true : false;
    }

    @Override
    public boolean editResource(ConsoleResource resource) {
        if(resource == null || resource.getId() == null){
            return false;
        }

        int i = consoleResourceMapper.updateByPrimaryKeySelective(resource);

        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteResource(Integer id) {
        int i = consoleResourceMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }
}
