package com.tainy.service.impl;

import com.tainy.entity.UserInfo;
import com.tainy.mapper.UserInfoMapper;
import com.tainy.service.UserInfoService;
import com.tainy.util.md5.MD5Util;
import com.tainy.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * UserInfo服务接口实现类
 * @Service("userInfoService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/9/27
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo login(UserInfo userInfo) {
        userInfo.setPassword(MD5Util.md5(userInfo.getPassword()));
        return userInfoMapper.login(userInfo);
    }

    @Override
    public List<UserInfo> listAllUserInfo(Page<?> page, UserInfo userInfo) {
        return userInfoMapper.selectByPageListAllUserInfo(page, userInfo);
    }

    @Override
    public boolean judgeUserNameIsExist(String userName) {
        List<UserInfo> isExistList = userInfoMapper.judgeUserNameIsExist(userName);

        if(!CollectionUtils.isEmpty(isExistList)){
            return true;
        }

        return false;
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        userInfo.setPassword(MD5Util.md5(userInfo.getPassword()));
        return userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public List<UserInfo> listUserInfoByObject(UserInfo userInfo) {
        return userInfoMapper.listUserInfoByObject(userInfo);
    }

    @Override
    public int editUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public int deleteUserInfo(UserInfo userInfo) {
        return userInfoMapper.deleteByPrimaryKey(userInfo.getId());
    }
}
