package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.BaseController;
import com.tainy.common.BaseRequest;
import com.tainy.common.BaseResponse;
import com.tainy.entity.DeptInfo;
import com.tainy.entity.JobInfo;
import com.tainy.entity.Organization;
import com.tainy.service.DeptInfoService;
import com.tainy.service.JobInfoService;
import com.tainy.service.OrganizationService;
import com.tainy.vo.init.InitDataRequest;
import com.tainy.vo.init.InitDataResponse;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理组织机构请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/organization")
public class OrganizationController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DeptInfoService deptInfoService;

    @Autowired
    private JobInfoService jobInfoService;

    @RequestMapping(value = "/initData", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "初始化数据接口", httpMethod = "POST", response = BaseResponse.class, notes = "初始化数据")
    public ResponseEntity<String> initData(@RequestBody BaseRequest<InitDataRequest> baseRequest) {
        LOGGER.info("初始化数据请求数据：" + JSON.toJSONString(baseRequest));
        baseRequest.validate();

        List<Organization> organizationList = organizationService.listOrganization();

        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setDelFlag((short)0);
        List<DeptInfo> deptInfoList = deptInfoService.listDeptInfoByObject(deptInfo);

        JobInfo jobInfo = new JobInfo();
        jobInfo.setDelFlag((short)0);
        List<JobInfo> jobInfoList = jobInfoService.listJobInfoByObject(jobInfo);

        //封装数据
        InitDataResponse response = new InitDataResponse();
        response.setOrganizationList(organizationList);
        response.setDeptInfoList(deptInfoList);
        response.setJobInfoList(jobInfoList);

        return responseData(BaseResponse.success("登录成功", response));
    }
}
