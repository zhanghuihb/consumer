package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsoleUserMapper extends BaseMapper<ConsoleUser> {

    ConsoleUser findUserByUsername(String username);

    List<ConsoleUser> selectListPageGetUsers(@Param("page") Page<?> page, @Param("user") ConsoleUser user);

}