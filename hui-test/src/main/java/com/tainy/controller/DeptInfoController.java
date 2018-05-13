package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.*;
import com.tainy.entity.DeptInfo;
import com.tainy.service.DeptInfoService;
import com.tainy.util.page.Page;
import com.tainy.vo.dept.AddDeptInfoRequest;
import com.tainy.vo.dept.DeleteDeptInfoRequest;
import com.tainy.vo.dept.EditDeptInfoRequest;
import com.tainy.vo.dept.ListDeptInfoRequest;
import com.wordnik.swagger.annotations.ApiOperation;
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
 * 处理部门请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/deptInfo")
public class DeptInfoController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptInfoController.class);

    @Autowired
    private DeptInfoService deptInfoService;

    @RequestMapping(value = "/listAllDeptInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "查询所部门接口", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有部门")
    public ResponseEntity<String> listAllDeptInfo(@RequestBody BaseRequest<ListDeptInfoRequest> baseRequest){
        LOGGER.info("查询所有部门请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        ListDeptInfoRequest listDeptInfoRequest = baseRequest.getParam();
        DeptInfo deptInfo = new DeptInfo();
        BeanUtils.copyProperties(listDeptInfoRequest, deptInfo);

        List<DeptInfo> resutList = deptInfoService.listAllDeptInfo(page, deptInfo);

        Page<DeptInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);

        if(!CollectionUtils.isEmpty(resutList)){
            responsePage.setList(resutList);
            return responseData(BaseResponse.success("查询所有部门成功", responsePage));
        }else{
            return responseData(BaseResponse.success("查询所有部门为空", responsePage));
        }
    }

    private boolean judgeDeptNameIsExist(String name, boolean isEdit, Long deptId){
        DeptInfo deptInfo = new DeptInfo();
        deptInfo.setName(name);
        deptInfo.setDelFlag(Constant.DEL_FLAG_NO);
        List<DeptInfo> deptInfoList = deptInfoService.listDeptInfoByObject(deptInfo);
        boolean isDeptNameExist = CollectionUtils.isEmpty(deptInfoList);
        if(isEdit && !isDeptNameExist){
            if(deptInfoList.size() == 1 && deptId == deptInfoList.get(0).getId()){
                isDeptNameExist = true;
            }
        }

        return isDeptNameExist;
    }

    @RequestMapping(value = "/addDeptInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "新增部门接口", httpMethod = "POST", response = BaseResponse.class, notes = "新增部门")
    public ResponseEntity<String> addDeptInfo(@RequestBody BaseRequest<AddDeptInfoRequest> baseRequest){
        LOGGER.info("新增部门请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        AddDeptInfoRequest addDeptInfoRequest = baseRequest.getParam();

        DeptInfo deptInfo = new DeptInfo();
        BeanUtils.copyProperties(addDeptInfoRequest, deptInfo);

        //登陆名不能重复
        if(!judgeDeptNameIsExist(deptInfo.getName(),false,null)){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"部门名称已存在"));
        }

        deptInfo.setDelFlag(Constant.DEL_FLAG_NO);
        
        int i = deptInfoService.addDeptInfo(deptInfo);

        if(i > 0){
            return responseData(BaseResponse.success("新增部门成功", deptInfo));
        }else{
            return responseData(BaseResponse.fail("新增部门失败"));
        }
    }

    @RequestMapping(value = "/editDeptInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "编辑部门接口", httpMethod = "POST", response = BaseResponse.class, notes = "编辑部门")
    public ResponseEntity<String> editDeptInfo(@RequestBody BaseRequest<EditDeptInfoRequest> baseRequest){
        LOGGER.info("编辑部门请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        EditDeptInfoRequest editDeptInfoRequest = baseRequest.getParam();

        DeptInfo deptInfo = new DeptInfo();
        BeanUtils.copyProperties(editDeptInfoRequest, deptInfo);

        //登陆名不能重复
        if(!judgeDeptNameIsExist(deptInfo.getName(), true, deptInfo.getId())){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"部门名称已存在"));
        }

        int i = deptInfoService.editDeptInfo(deptInfo);

        if(i > 0){
            return responseData(BaseResponse.success("编辑部门成功", deptInfo));
        }else{
            return responseData(BaseResponse.fail("编辑部门失败"));
        }
    }

    @RequestMapping(value = "/deleteDeptInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "删除部门接口", httpMethod = "POST", response = BaseResponse.class, notes = "删除部门")
    public ResponseEntity<String> deleteDeptInfo(@RequestBody BaseRequest<DeleteDeptInfoRequest> baseRequest){
        LOGGER.info("删除部门请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        DeleteDeptInfoRequest deleteDeptInfoRequest = baseRequest.getParam();

        DeptInfo deptInfo = new DeptInfo();
        BeanUtils.copyProperties(deleteDeptInfoRequest, deptInfo);

        int i = deptInfoService.deleteDeptInfo(deptInfo);

        if(i > 0){
            return responseData(BaseResponse.success("删除部门成功"));
        }else{
            return responseData(BaseResponse.fail("删除部门失败"));
        }
    }
}
