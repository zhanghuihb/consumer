package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.util.page.Page;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public interface ConsoleResourceService{

    List<ConsoleResource> getAllResources(ConsoleResource resource);

    List<ConsoleResource> selectListPageGetResources(Page<?> page, ConsoleResource resource);

    boolean addResource(ConsoleResource resource);

    boolean editResource(ConsoleResource resource);

    boolean deleteResource(Integer id);

}
