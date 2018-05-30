package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.*;
import com.tainy.entity.UserInfo;
import com.tainy.service.UserInfoService;
import com.tainy.util.page.Page;
import com.tainy.vo.user.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 处理用户请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/userInfo")
public class UserInfoController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "登录接口", httpMethod = "POST", response = BaseResponse.class, notes = "根据用户名和密码登录")
    public ResponseEntity<String> login(@RequestBody BaseRequest<LoginRequest> baseRequest){
        LOGGER.info("登录接口请求数据：" + JSON.toJSONString(baseRequest));
        baseRequest.validate();

        LoginRequest loginRequest = baseRequest.getParam();

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(loginRequest, userInfo);

        userInfo = userInfoService.login(userInfo);
        if(userInfo != null && userInfo.getId() != null){
            return responseData(BaseResponse.success("登录成功", userInfo));
        }else{
            return responseData(BaseResponse.fail("用户名或密码不正确，登录失败"));
        }
    }

    @RequestMapping(value = "/listAllUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "查询所有用户接口", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有用户")
    public ResponseEntity<String> listAllUserInfo(@RequestBody BaseRequest<ListUserInfoRequest> baseRequest){
        LOGGER.info("查询所有用户请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        ListUserInfoRequest listUserInfoRequest = baseRequest.getParam();
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(listUserInfoRequest, userInfo);

        List<UserInfo> resutList = userInfoService.listAllUserInfo(page, userInfo);

        Page<UserInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);

        if(!CollectionUtils.isEmpty(resutList)){
            responsePage.setList(resutList);
            return responseData(BaseResponse.success("查询所有用户成功", responsePage));
        }else{
            return responseData(BaseResponse.success("查询所有用户为空", responsePage));
        }
    }

    private boolean judgeUserNameIsExist(String userName, boolean isEdit, Long userId){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setDelFlag(Constant.DEL_FLAG_NO);
        List<UserInfo> userInfoList = userInfoService.listUserInfoByObject(userInfo);
        boolean isUserNameExist = CollectionUtils.isEmpty(userInfoList);
        if(isEdit && !isUserNameExist){
            if(userInfoList.size() == 1 && userId == userInfoList.get(0).getId()){
                isUserNameExist = true;
            }
        }

        return isUserNameExist;
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "新增用户接口", httpMethod = "POST", response = BaseResponse.class, notes = "新增用户")
    public ResponseEntity<String> addUserInfo(@RequestBody BaseRequest<AddUserInfoRequest> baseRequest){
        LOGGER.info("新增用户请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        AddUserInfoRequest addUserInfoRequest = baseRequest.getParam();

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(addUserInfoRequest, userInfo);

        //登陆名不能重复
        if(!judgeUserNameIsExist(userInfo.getUserName(),false,null)){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"登录名称已存在"));
        }

        Date now = new Date();
        userInfo.setCreateTime(now);
        userInfo.setDelFlag(Constant.DEL_FLAG_NO);
        
        int i = userInfoService.addUserInfo(userInfo);

        if(i > 0){
            return responseData(BaseResponse.success("新增用户成功", userInfo));
        }else{
            return responseData(BaseResponse.fail("新增用户失败"));
        }
    }

    @RequestMapping(value = "/editUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "编辑用户接口", httpMethod = "POST", response = BaseResponse.class, notes = "编辑用户")
    public ResponseEntity<String> editUserInfo(@RequestBody BaseRequest<EditUserInfoRequest> baseRequest){
        LOGGER.info("编辑用户请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        EditUserInfoRequest editUserInfoRequest = baseRequest.getParam();

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(editUserInfoRequest, userInfo);

        //登陆名不能重复
        if(!judgeUserNameIsExist(userInfo.getUserName(), true, userInfo.getId())){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"登录名称已存在"));
        }

        int i = userInfoService.editUserInfo(userInfo);

        if(i > 0){
            return responseData(BaseResponse.success("编辑用户成功", userInfo));
        }else{
            return responseData(BaseResponse.fail("编辑用户失败"));
        }
    }

    @RequestMapping(value = "/deleteUserInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "删除用户接口", httpMethod = "POST", response = BaseResponse.class, notes = "删除用户")
    public ResponseEntity<String> deleteUserInfo(@RequestBody BaseRequest<DeleteUserInfoRequest> baseRequest){
        LOGGER.info("删除用户请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        DeleteUserInfoRequest deleteUserInfoRequest = baseRequest.getParam();

        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(deleteUserInfoRequest, userInfo);

        int i = userInfoService.deleteUserInfo(userInfo);

        if(i > 0){
            return responseData(BaseResponse.success("删除用户成功"));
        }else{
            return responseData(BaseResponse.fail("删除用户失败"));
        }
    }
}
