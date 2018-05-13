package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleResource;
import com.tainy.common.domain.console.ConsoleUser;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.util.page.Page;
import com.tainy.console.service.ConsoleResourceService;
import com.tainy.console.vo.console.AddResourceRequest;
import com.tainy.console.vo.console.DeleteResourceRequest;
import com.tainy.console.vo.console.EditResourceRequest;
import com.tainy.console.vo.console.GetResourcesRequest;
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
@RequestMapping("/v1/consoleResource")
public class ConsoleResourceController extends BaseController {

    @Autowired
    private ConsoleResourceService consoleResourceService;

    @RequestMapping("/getAllResources")
    @ResponseBody
    @ApiOperation(value = "查询所有权限接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getAllResources(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询所有权限请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();
        ConsoleResource resource = new ConsoleResource();
        resource.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleResource> resultList = consoleResourceService.getAllResources(resource);

        return responseData(BaseResponse.success("查询所有权限成功", resultList));
    }

    @RequestMapping("/getResources")
    @ResponseBody
    @ApiOperation(value = "查询权限接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getResources(@RequestBody BaseRequest<GetResourcesRequest> baseRequest){
        LOGGER.info("查询权限请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        String queryName = baseRequest.getParam().getQueryName();
        ConsoleResource resource = new ConsoleResource();
        if(!StringUtil.isEmpty(queryName)){
            resource.setName(queryName);
        }
        resource.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleResource> resultList = consoleResourceService.selectListPageGetResources(page, resource);

        Page<ConsoleResource> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);
        responsePage.setList(resultList);

        return responseData(BaseResponse.success("查询权限成功", responsePage));
    }

    @RequestMapping("/addResource")
    @ResponseBody
    @ApiOperation(value = "新增权限接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> addResource(@RequestBody BaseRequest<AddResourceRequest> baseRequest){
        LOGGER.info("新增权限请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        AddResourceRequest addResourceRequest = baseRequest.getParam();
        ConsoleResource resource = new ConsoleResource();
        BeanUtils.copyProperties(addResourceRequest, resource);

        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");

        Date now = new Date();
        resource.setCreateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        resource.setCreateTime(now);
        resource.setUpdateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        resource.setUpdateTime(now);
        resource.setDelFlag(1);
        resource.setStatus(1);

        boolean isSuccess = consoleResourceService.addResource(resource);

        if(isSuccess){
            resource.setPath(Constants.RESOURCE_VIEW);
            return responseData(BaseResponse.success("新增权限成功", resource));
        }else{
            return responseData(BaseResponse.fail("新增权限失败"));
        }
    }

    @RequestMapping("/editResource")
    @ResponseBody
    @ApiOperation(value = "编辑权限接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> editResource(@RequestBody BaseRequest<EditResourceRequest> baseRequest){
        LOGGER.info("编辑权限请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        EditResourceRequest editResourceRequest = baseRequest.getParam();
        ConsoleResource resource = new ConsoleResource();
        BeanUtils.copyProperties(editResourceRequest, resource);

        Object obj = SecurityUtils.getSubject().getSession().getAttribute("currentUser");

        Date now = new Date();
        resource.setUpdateUser(obj != null ? ((ConsoleUser) obj).getId().toString() : "0");
        resource.setUpdateTime(now);

        boolean isSuccess = consoleResourceService.editResource(resource);

        if(isSuccess){
            resource.setPath(Constants.RESOURCE_VIEW);
            return responseData(BaseResponse.success("编辑权限成功", resource));
        }else{
            return responseData(BaseResponse.fail("编辑权限失败"));
        }
    }

    @RequestMapping("/deleteResource")
    @ResponseBody
    @ApiOperation(value = "删除权限接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> deleteResource(@RequestBody BaseRequest<DeleteResourceRequest> baseRequest){
        LOGGER.info("删除权限请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        DeleteResourceRequest deleteResourceRequest = baseRequest.getParam();
        Integer id = deleteResourceRequest.getId();

        boolean isSuccess = consoleResourceService.deleteResource(id);

        if(isSuccess){
            return responseData(BaseResponse.success("删除权限成功", Constants.RESOURCE_VIEW));
        }else{
            return responseData(BaseResponse.fail("删除权限失败"));
        }
    }
}
