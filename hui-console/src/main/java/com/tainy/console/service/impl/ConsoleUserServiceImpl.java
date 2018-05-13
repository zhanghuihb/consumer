package com.tainy.console.service.impl;

import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.CheckUserUtil;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.util.page.Page;
import com.tainy.console.mapper.ConsoleRoleMapper;
import com.tainy.console.mapper.ConsoleUserMapper;
import com.tainy.console.service.ConsoleUserService;
import com.tainy.console.shiro.utils.ShiroUtils;
import com.tainy.console.vo.console.AddUserRequest;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ConsoleUser服务接口实现类
 * @Service("consoleUserService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/9/27
 */
@Service
public class ConsoleUserServiceImpl implements ConsoleUserService {

    @Autowired
    private ConsoleUserMapper consoleUserMapper;

    @Autowired
    private ConsoleRoleMapper consoleRoleMapper;

    public ConsoleUser findUserByUsername(String username) {
        return consoleUserMapper.findUserByUsername(username);
    }

    @Override
    public ConsoleUser addUser(AddUserRequest addUserRequest) {
        ConsoleUser user = CheckUserUtil.getUser();

        Date now = new Date();
        ConsoleUser consoleUser = new ConsoleUser();
        consoleUser.setUsername(addUserRequest.getUsername());
        consoleUser.setRoleIds(addUserRequest.getRoleIds());
        consoleUser.setStatus(Constant.USER_NORMAL);

        consoleUser.setDelFlag(Constant.DEL_FLAG_NO);
        consoleUser.setCreateTime(now);
        consoleUser.setCreateUser(user == null ? "0" : user.getId().toString());
        consoleUser.setUpdateTime(now);
        consoleUser.setUpdateUser(user == null ? "0" : user.getId().toString());

        String salt = ShiroUtils.genSalt();
        String pwd = ShiroUtils.pwdEncryption("MD5", addUserRequest.getUsername(), addUserRequest.getPassword(), salt, 3);

        consoleUser.setSalt(salt);
        consoleUser.setPassword(pwd);

        int i = consoleUserMapper.insertSelective(consoleUser);
        if(i == 0){
            return null;
        }
        consoleUser.setPath(Constants.USER_VIEW);
        return consoleUser;
    }

    @Override
    public List<ConsoleUser> selectListPageGetUsers(Page<?> page, ConsoleUser consoleUser) {
        List<ConsoleUser> resultList = consoleUserMapper.selectListPageGetUsers(page, consoleUser);
        if(!CollectionUtils.isEmpty(resultList)){
            List<ConsoleRole> roleList = null;
            for(ConsoleUser u : resultList){
                roleList = new ArrayList<>();
                if(!StringUtil.isEmpty(u.getRoleIds())){
                    String[] rolesIds = u.getRoleIds().split(",");
                    roleList = consoleRoleMapper.queryRolesByRoleIds(rolesIds);
                }
                u.setRoleList(roleList);
            }
        }
        return resultList;
    }

    @Override
    public boolean editUser(ConsoleUser consoleUser) {
        if(consoleUser == null || consoleUser.getId() == null){
            return false;
        }

        ConsoleUser user = consoleUserMapper.selectByPrimaryKey(consoleUser.getId());
        if(user == null || user.getSalt() == null){
            return false;
        }

        int i = consoleUserMapper.updateByPrimaryKeySelective(consoleUser);

        return i > 0 ? true : false;
    }

    @Override
    public boolean deleteUser(Integer id) {
        int i = consoleUserMapper.deleteByPrimaryKey(id);
        return i > 0 ? true : false;
    }
}
