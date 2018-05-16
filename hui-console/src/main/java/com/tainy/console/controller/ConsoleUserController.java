package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.util.page.Page;
import com.tainy.console.service.ConsoleUserService;
import com.tainy.console.vo.console.*;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author Tainy
 * @date 2017/12/20
 */
@Controller
@RequestMapping("/v1/consoleUser")
public class ConsoleUserController extends BaseController{

    @Autowired
    private ConsoleUserService consoleUserService;

    @RequestMapping("/login")
    @ResponseBody
    @ApiOperation(value = "登录接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> login(@RequestBody BaseRequest<LoginRequest> baseRequest){
        LOGGER.info("登录请求参数" + baseRequest);
        baseRequest.validate();

        String username = baseRequest.getParam().getUsername();
        String password = baseRequest.getParam().getPassword();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        LOGGER.info("登录令牌token=" + token);

        Subject currentUser = SecurityUtils.getSubject();

        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            LOGGER.info("user[" + username + "]do login checking");
            currentUser.login(token);
            LOGGER.info("user[" + username + "]authentication success");

        }catch (IncorrectCredentialsException e) {
            return responseData(BaseResponse.fail("登录账号或密码错误!"));
        } catch (ExcessiveAttemptsException e) {
            return responseData(BaseResponse.fail("登录失败次数过多, 10分钟后重新登录!"));
        } catch (LockedAccountException e) {
            return responseData(BaseResponse.fail("帐号已被锁定!"));
        } catch (DisabledAccountException e) {
            return responseData(BaseResponse.fail("帐号已被禁用!"));
        } catch (ExpiredCredentialsException e) {
            return responseData(BaseResponse.fail("帐号已过期!"));
        } catch (UnknownAccountException e) {
            return responseData(BaseResponse.fail("帐号不存在!"));
        } catch (UnauthorizedException e) {
            return responseData(BaseResponse.fail("您没有得到相应的授权!"));
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            LOGGER.info("user[" + username + "]authentication success");
            return responseData(BaseResponse.success("登录成功!", Constants.MAIN_PAGE));
        }
        token.clear();
        return responseData(BaseResponse.fail("登录信息验证失败!"));

    }

    @RequestMapping("/logout")
    @ResponseBody
    @ApiOperation(value = "退出登录接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> logout(@RequestBody BaseRequest<?> baseRequest){
        SecurityUtils.getSubject().logout();
        return responseData(BaseResponse.success("退出登录成功!", Constants.LOGIN_PAGE));
    }

    @RequestMapping("/getUsers")
    @ResponseBody
    @ApiOperation(value = "查询用户接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getUsers(@RequestBody BaseRequest<GetUsersRequest> baseRequest){
        LOGGER.info("查询用户请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        String queryUsername = baseRequest.getParam().getQueryUsername();
        ConsoleUser user = new ConsoleUser();
        if(!StringUtil.isEmpty(queryUsername)){
            user.setUsername(queryUsername);
        }
        user.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleUser> resultList = consoleUserService.selectListPageGetUsers(page, user);

        Page<ConsoleUser> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);
        responsePage.setList(resultList);

        return responseData(BaseResponse.success("查询用户成功", responsePage));
    }

    @RequestMapping("/addUser")
    @ResponseBody
    @ApiOperation(value = "添加用户接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> addUser(@RequestBody BaseRequest<AddUserRequest> baseRequest) {
        LOGGER.info("添加用户请求参数{}" + baseRequest);
        baseRequest.validate();

        ConsoleUser user = consoleUserService.addUser(baseRequest.getParam());

        if(user != null && user.getId() != null){
            return responseData(BaseResponse.success("添加用户成功!", user));
        }else{
            return responseData(BaseResponse.fail("添加用户失败!"));
        }
    }

    @RequestMapping("/editUser")
    @ResponseBody
    @ApiOperation(value = "编辑用户接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> editUser(@RequestBody BaseRequest<EditUserRequest> baseRequest){
        LOGGER.info("编辑用户请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        EditUserRequest editUserRequest = baseRequest.getParam();
        ConsoleUser user = new ConsoleUser();
        BeanUtils.copyProperties(editUserRequest, user);

        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");

        Date now = new Date();
        user.setUpdateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        user.setUpdateTime(now);

        boolean isSuccess = consoleUserService.editUser(user);

        if(isSuccess){
            user.setPath(Constants.USER_VIEW);
            return responseData(BaseResponse.success("编辑用户成功", user));
        }else{
            return responseData(BaseResponse.fail("编辑用户失败"));
        }
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    @ApiOperation(value = "删除用户接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> deleteRole(@RequestBody BaseRequest<DeleteUserRequest> baseRequest){
        LOGGER.info("删除用户请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        DeleteUserRequest deleteUserRequest = baseRequest.getParam();
        Integer id = deleteUserRequest.getId();

        boolean isSuccess = consoleUserService.deleteUser(id);

        if(isSuccess){
            return responseData(BaseResponse.success("删除用户成功", Constants.ROLE_VIEW));
        }else{
            return responseData(BaseResponse.fail("删除用户失败"));
        }
    }
}
