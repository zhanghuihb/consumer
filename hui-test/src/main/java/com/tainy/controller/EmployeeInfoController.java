package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.*;
import com.tainy.entity.EmployeeInfo;
import com.tainy.service.EmployeeInfoService;
import com.tainy.util.page.Page;
import com.tainy.vo.employee.AddEmployeeInfoRequest;
import com.tainy.vo.employee.DeleteEmployeeInfoRequest;
import com.tainy.vo.employee.EditEmployeeInfoRequest;
import com.tainy.vo.employee.ListEmployeeInfoRequest;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 处理员工请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/employeeInfo")
public class EmployeeInfoController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeInfoController.class);

    @Autowired
    private EmployeeInfoService employeeInfoService;

    @RequestMapping(value = "/listAllEmployeeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "查询所员工接口", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有员工")
    public ResponseEntity<String> listAllEmployeeInfo(@RequestBody BaseRequest<ListEmployeeInfoRequest> baseRequest){
        LOGGER.info("查询所有员工请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        ListEmployeeInfoRequest listEmployeeInfoRequest = baseRequest.getParam();
        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(listEmployeeInfoRequest, employeeInfo);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<EmployeeInfo> resultList = employeeInfoService.listAllEmployeeInfo(page, employeeInfo);
        if(!CollectionUtils.isEmpty(resultList)){
            for(EmployeeInfo emp : resultList){
                if(emp.getBirthday() != null){
                    if(emp.getBirthday() != null){
                        String birthday = sdf.format(emp.getBirthday());

                        StringBuilder sb = new StringBuilder(birthday);
                        sb.insert(10, "T");
                        birthday = sb.toString();
                        birthday = birthday.replace(" ", "");

                        emp.setBirthdayEdit(birthday);
                    }

                }
            }
        }

        Page<EmployeeInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);

        if(!CollectionUtils.isEmpty(resultList)){
            responsePage.setList(resultList);
            return responseData(BaseResponse.success("查询所有员工成功", responsePage));
        }else{
            return responseData(BaseResponse.success("查询所有员工为空", responsePage));
        }
    }

    private boolean judgeEmployeeCardIdIsExist(String cardId, boolean isEdit, Long employeeId){
        EmployeeInfo employeeInfo = new EmployeeInfo();
        employeeInfo.setCardId(cardId);
        employeeInfo.setDelFlag(Constant.DEL_FLAG_NO);
        List<EmployeeInfo> employeeInfoList = employeeInfoService.listEmployeeInfoByObject(employeeInfo);
        boolean isEmployeeCardIdExist = CollectionUtils.isEmpty(employeeInfoList);
        if(isEdit && !isEmployeeCardIdExist){
            if(employeeInfoList.size() == 1 && employeeId == employeeInfoList.get(0).getId()){
                isEmployeeCardIdExist = true;
            }
        }

        return isEmployeeCardIdExist;
    }

    @RequestMapping(value = "/addEmployeeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "新增员工接口", httpMethod = "POST", response = BaseResponse.class, notes = "新增员工")
    public ResponseEntity<String> addEmployeeInfo(@RequestBody BaseRequest<AddEmployeeInfoRequest> baseRequest){
        LOGGER.info("新增员工请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        AddEmployeeInfoRequest addEmployeeInfoRequest = baseRequest.getParam();

        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(addEmployeeInfoRequest, employeeInfo);

        String birthday = addEmployeeInfoRequest.getBirthday();
        birthday = birthday + ":00";
        birthday = birthday.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            employeeInfo.setBirthday(sdf.parse(birthday));
        } catch (ParseException e) {
            LOGGER.info("员工生日格式化失败", JSON.toJSONString(birthday));
            e.printStackTrace();
        }

        //登陆名不能重复
        if(!judgeEmployeeCardIdIsExist(employeeInfo.getCardId(),false,null)){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"员工身份证号已存在"));
        }
        Date now = new Date();
        employeeInfo.setCreateTime(now);
        employeeInfo.setDelFlag(Constant.DEL_FLAG_NO);
        
        int i = employeeInfoService.addEmployeeInfo(employeeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("新增员工成功", employeeInfo));
        }else{
            return responseData(BaseResponse.fail("新增员工失败"));
        }
    }

    @RequestMapping(value = "/editEmployeeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "编辑员工接口", httpMethod = "POST", response = BaseResponse.class, notes = "编辑员工")
    public ResponseEntity<String> editEmployeeInfo(@RequestBody BaseRequest<EditEmployeeInfoRequest> baseRequest){
        LOGGER.info("编辑员工请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        EditEmployeeInfoRequest editEmployeeInfoRequest = baseRequest.getParam();

        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(editEmployeeInfoRequest, employeeInfo);

        //登陆名不能重复
        if(!judgeEmployeeCardIdIsExist(employeeInfo.getCardId(), true, employeeInfo.getId())){
            return responseData(BaseResponse.fail(ErrorCodeConstant.ERR_CODE_EXIST,"员工身份证号已存在"));
        }

        int i = employeeInfoService.editEmployeeInfo(employeeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("编辑员工成功", employeeInfo));
        }else{
            return responseData(BaseResponse.fail("编辑员工失败"));
        }
    }

    @RequestMapping(value = "/deleteEmployeeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "删除员工接口", httpMethod = "POST", response = BaseResponse.class, notes = "删除员工")
    public ResponseEntity<String> deleteEmployeeInfo(@RequestBody BaseRequest<DeleteEmployeeInfoRequest> baseRequest){
        LOGGER.info("删除员工请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        DeleteEmployeeInfoRequest deleteEmployeeInfoRequest = baseRequest.getParam();

        EmployeeInfo employeeInfo = new EmployeeInfo();
        BeanUtils.copyProperties(deleteEmployeeInfoRequest, employeeInfo);

        int i = employeeInfoService.deleteEmployeeInfo(employeeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("删除员工成功"));
        }else{
            return responseData(BaseResponse.fail("删除员工失败"));
        }
    }
}
