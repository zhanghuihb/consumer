package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.UserInfo;
import com.tainy.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 根据登录名和密码查询用户
     *
     * @param userInfo
     * @return 找到返回UserInfo对象，没有找到返回null
     */
    UserInfo login(@Param("userInfo") UserInfo userInfo);

    /**
     * 根据查询用户总数
     *
     * @param userInfo
     * @return
     */
    Long count(@Param("userInfo") UserInfo userInfo);

    /**
     * 获得所有用户（分页查询）
     *
     * @return
     */
    List<UserInfo> selectByPageListAllUserInfo(@Param("page") Page<?> page, @Param("userInfo") UserInfo userInfo);

    /**
     * 判断 用户名是否存在
     *
     * @param userName
     * @return
     */
    List<UserInfo> judgeUserNameIsExist(@Param("userName") String userName);

    /**
     * 获得用户
     *
     * @param userInfo
     * @return
     */
    List<UserInfo> listUserInfoByObject(UserInfo userInfo);
}