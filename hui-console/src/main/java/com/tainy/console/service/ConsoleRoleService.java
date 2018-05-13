package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.util.page.Page;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/6
 */
public interface ConsoleRoleService {

    List<ConsoleRole> getAllRoles(ConsoleRole role);

    List<ConsoleRole> selectListPageGetRoles(Page<?> page, ConsoleRole consoleRole);

    boolean addRole(ConsoleRole consoleRole);

    boolean editRole(ConsoleRole consoleRole);

    boolean deleteRole(Integer id);

}
