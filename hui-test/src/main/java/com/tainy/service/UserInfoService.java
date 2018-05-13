package com.tainy.service;

import com.tainy.entity.UserInfo;
import com.tainy.util.page.Page;

import java.util.List;

/**
 * User服务层接口
 * @author Tainy
 * @date 2017/9/27
 */
public interface UserInfoService{

    /**
     * 判断用户登录
     *
     * @param userInfo
     * @return 找到返回User对象，没有找到返回null
     */
    UserInfo login(UserInfo userInfo);

    /**
     * 获得所有用户
     *
     * @return
     */
    List<UserInfo> listAllUserInfo(Page<?> page, UserInfo userInfo);

    /**
     * 判断 用户名是否存在
     *
     * @param userName
     * @return
     */
    boolean judgeUserNameIsExist(String userName);

    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    int addUserInfo(UserInfo userInfo);

    /**
     * 获得用户
     *
     * @return
     */
    List<UserInfo> listUserInfoByObject(UserInfo userInfo);

    /**
     * 新增用户
     *
     * @param userInfo
     * @return
     */
    int editUserInfo(UserInfo userInfo);

    /**
     * 删除用户
     *
     * @param userInfo
     * @return
     */
    int deleteUserInfo(UserInfo userInfo);
}
