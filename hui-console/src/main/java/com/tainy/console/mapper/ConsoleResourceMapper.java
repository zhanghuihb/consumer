package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsoleResourceMapper extends BaseMapper<ConsoleResource>{

    List<ConsoleResource> selectListPageGetResources(@Param("page") Page<?> page, @Param("resource") ConsoleResource resource);

    List<ConsoleResource> queryResourcesByResourcesIds(@Param("resourcesIds") String[] resourcesIds);
}