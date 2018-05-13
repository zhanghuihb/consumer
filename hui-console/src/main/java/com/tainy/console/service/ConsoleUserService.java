package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.page.Page;
import com.tainy.console.vo.console.AddUserRequest;

import java.util.List;

/**
 * ConsoleUser服务层接口
 * @author Tainy
 * @date 2017/9/27
 */
public interface ConsoleUserService {

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return
     */
    ConsoleUser findUserByUsername(String username);

    /**
     * 新增用户
     *
     * @param addUserRequest
     * @return
     */
    ConsoleUser addUser(AddUserRequest addUserRequest);

    List<ConsoleUser> selectListPageGetUsers(Page<?> page, ConsoleUser consoleUser);

    boolean editUser(ConsoleUser cnsoleUser);

    boolean deleteUser(Integer id);
}
