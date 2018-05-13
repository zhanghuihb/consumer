package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.domain.console.ConsoleRole;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.util.page.Page;
import com.tainy.console.service.ConsoleRoleService;
import com.tainy.console.vo.console.*;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
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
 * @date 2018/1/6
 */
@Controller
@RequestMapping("/v1/consoleRole")
public class ConsoleRoleController extends BaseController {

    @Autowired
    private ConsoleRoleService consoleRoleService;

    @RequestMapping("/getAllRoles")
    @ResponseBody
    @ApiOperation(value = "查询所有角色接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getAllRoles(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询所有角色请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();
        ConsoleRole role = new ConsoleRole();
        role.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleRole> resultList = consoleRoleService.getAllRoles(role);

        return responseData(BaseResponse.success("查询所有角色成功", resultList));
    }

    @RequestMapping("/getRoles")
    @ResponseBody
    @ApiOperation(value = "查询角色接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getRoles(@RequestBody BaseRequest<GetRolesRequest> baseRequest){
        LOGGER.info("查询角色请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        String queryRole = baseRequest.getParam().getQueryRole();
        ConsoleRole role = new ConsoleRole();
        if(!StringUtil.isEmpty(queryRole)){
            role.setRole(queryRole);
        }
        role.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleRole> resultList = consoleRoleService.selectListPageGetRoles(page, role);

        Page<ConsoleRole> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);
        responsePage.setList(resultList);

        return responseData(BaseResponse.success("查询角色成功", responsePage));
    }

    @RequestMapping("/addRole")
    @ResponseBody
    @ApiOperation(value = "新增角色接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> addRole(@RequestBody BaseRequest<AddRoleRequest> baseRequest){
        LOGGER.info("新增角色请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        AddRoleRequest addRoleRequest = baseRequest.getParam();
        ConsoleRole role = new ConsoleRole();
        BeanUtils.copyProperties(addRoleRequest, role);

        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");

        Date now = new Date();
        role.setCreateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        role.setCreateTime(now);
        role.setUpdateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        role.setUpdateTime(now);
        role.setDelFlag(1);
        role.setStatus(1);

        boolean isSuccess = consoleRoleService.addRole(role);

        if(isSuccess){
            role.setPath(Constants.ROLE_VIEW);
            return responseData(BaseResponse.success("新增角色成功", role));
        }else{
            return responseData(BaseResponse.fail("新增角色失败"));
        }
    }

    @RequestMapping("/editRole")
    @ResponseBody
    @ApiOperation(value = "编辑角色接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> editRole(@RequestBody BaseRequest<EditRoleRequest> baseRequest){
        LOGGER.info("编辑角色请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        EditRoleRequest editRoleRequest = baseRequest.getParam();
        ConsoleRole role = new ConsoleRole();
        BeanUtils.copyProperties(editRoleRequest, role);

        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");

        Date now = new Date();
        role.setUpdateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        role.setUpdateTime(now);

        boolean isSuccess = consoleRoleService.editRole(role);

        if(isSuccess){
            role.setPath(Constants.ROLE_VIEW);
            return responseData(BaseResponse.success("编辑权限成功", role));
        }else{
            return responseData(BaseResponse.fail("编辑权限失败"));
        }
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    @ApiOperation(value = "删除角色接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> deleteRole(@RequestBody BaseRequest<DeleteRoleRequest> baseRequest){
        LOGGER.info("删除角色请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        DeleteRoleRequest deleteRoleRequest = baseRequest.getParam();
        Integer id = deleteRoleRequest.getId();

        boolean isSuccess = consoleRoleService.deleteRole(id);

        if(isSuccess){
            return responseData(BaseResponse.success("删除角色成功", Constants.ROLE_VIEW));
        }else{
            return responseData(BaseResponse.fail("删除角色失败"));
        }
    }
}
