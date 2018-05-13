package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.ApiOperation;
import com.tainy.common.*;
import com.tainy.entity.JobInfo;
import com.tainy.service.JobInfoService;
import com.tainy.util.page.Page;
import com.tainy.vo.job.AddJobInfoRequest;
import com.tainy.vo.job.DeleteJobInfoRequest;
import com.tainy.vo.job.EditJobInfoRequest;
import com.tainy.vo.job.ListJobInfoRequest;
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

import java.util.List;

/**
 * 处理职位请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/jobInfo")
public class JobInfoController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(JobInfoController.class);

    @Autowired
    private JobInfoService JobInfoService;

    @RequestMapping(value = "/listAllJobInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "查询所有职位接口", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有职位")
    public ResponseEntity<String> listAllJobInfo(@RequestBody BaseRequest<ListJobInfoRequest> baseRequest){
        LOGGER.info("查询所有职位请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        ListJobInfoRequest listJobInfoRequest = baseRequest.getParam();
        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(listJobInfoRequest, jobInfo);

        List<JobInfo> resutList = JobInfoService.listAllJobInfo(page, jobInfo);

        Page<JobInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);

        if(!CollectionUtils.isEmpty(resutList)){
            responsePage.setList(resutList);
            return responseData(BaseResponse.success("查询所有职位成功", responsePage));
        }else{
            return responseData(BaseResponse.success("查询所有职位为空", responsePage));
        }
    }

    private boolean judgeJobNameIsExist(String name, boolean isEdit, Long deptId){
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName(name);
        jobInfo.setDelFlag(Constant.DEL_FLAG_NO);
        List<JobInfo> jobInfoList = JobInfoService.listJobInfoByObject(jobInfo);
        boolean isJobNameExist = CollectionUtils.isEmpty(jobInfoList);
        if(isEdit && !isJobNameExist){
            if(jobInfoList.size() == 1 && deptId == jobInfoList.get(0).getId()){
                isJobNameExist = true;
            }
        }

        return isJobNameExist;
    }

    @RequestMapping(value = "/addJobInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "新增职位接口", httpMethod = "POST", response = BaseResponse.class, notes = "新增职位")
    public ResponseEntity<String> addJobInfo(@RequestBody BaseRequest<AddJobInfoRequest> baseRequest){
        LOGGER.info("新增职位请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        AddJobInfoRequest addJobInfoRequest = baseRequest.getParam();

        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(addJobInfoRequest, jobInfo);

        //登陆名不能重复
        if(!judgeJobNameIsExist(jobInfo.getName(),false,null)){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"职位名称已存在"));
        }

        jobInfo.setDelFlag(Constant.DEL_FLAG_NO);
        
        int i = JobInfoService.addJobInfo(jobInfo);

        if(i > 0){
            return responseData(BaseResponse.success("新增部门成功", jobInfo));
        }else{
            return responseData(BaseResponse.fail("新增部门失败"));
        }
    }

    @RequestMapping(value = "/editJobInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "编辑职位接口", httpMethod = "POST", response = BaseResponse.class, notes = "编辑职位")
    public ResponseEntity<String> editJobInfo(@RequestBody BaseRequest<EditJobInfoRequest> baseRequest){
        LOGGER.info("编辑职位请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        EditJobInfoRequest editJobInfoRequest = baseRequest.getParam();

        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(editJobInfoRequest, jobInfo);

        //登陆名不能重复
        if(!judgeJobNameIsExist(jobInfo.getName(), true, jobInfo.getId())){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"编辑名称已存在"));
        }

        int i = JobInfoService.editJobInfo(jobInfo);

        if(i > 0){
            return responseData(BaseResponse.success("编辑职位成功", jobInfo));
        }else{
            return responseData(BaseResponse.fail("编辑职位失败"));
        }
    }

    @RequestMapping(value = "/deleteJobInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "删除职位接口", httpMethod = "POST", response = BaseResponse.class, notes = "删除职位")
    public ResponseEntity<String> deleteJobInfo(@RequestBody BaseRequest<DeleteJobInfoRequest> baseRequest){
        LOGGER.info("删除职位请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        DeleteJobInfoRequest deleteJobInfoRequest = baseRequest.getParam();

        JobInfo jobInfo = new JobInfo();
        BeanUtils.copyProperties(deleteJobInfoRequest, jobInfo);

        int i = JobInfoService.deleteJobInfo(jobInfo);

        if(i > 0){
            return responseData(BaseResponse.success("删除职位成功"));
        }else{
            return responseData(BaseResponse.fail("删除职位失败"));
        }
    }
}
