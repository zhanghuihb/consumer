package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsoleRoleMapper extends BaseMapper<ConsoleRole> {

    List<ConsoleRole> selectListPageGetRoles(@Param("page") Page<?> page, @Param("role") ConsoleRole role);

    List<ConsoleRole> queryRolesByRoleIds(@Param("rolesIds") String[] rolesIds);
}