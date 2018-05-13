package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleConsumerInfo;
import com.tainy.common.util.DateUtil;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.util.page.Page;
import com.tainy.console.service.ConsoleConsumerInfoService;
import com.tainy.console.vo.console.*;
import com.tainy.console.vo.console.vo.GetConsumerInfosRequest;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/14
 */
@Controller
@RequestMapping("/v1/consoleConsumerInfo")
public class ConsoleConsumerInfoController extends BaseController{

    @Autowired
    private ConsoleConsumerInfoService consoleConsumerInfoService;

    @RequestMapping("/getConsumerInfos")
    @ResponseBody
    @ApiOperation(value = "查询所有消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getConsumerInfos(@RequestBody BaseRequest<GetConsumerInfosRequest> baseRequest){
        LOGGER.info("查询所有消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        Integer queryStatus = baseRequest.getParam().getQueryStatus();
        ConsoleConsumerInfo consoleConsumerInfo = new ConsoleConsumerInfo();
        if(queryStatus != null && queryStatus != -1){
            consoleConsumerInfo.setStatus(queryStatus);
        }
        consoleConsumerInfo.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleConsumerInfo> resultList = consoleConsumerInfoService.selectListPageGetConsumerInfos(page, consoleConsumerInfo);

        Page<ConsoleConsumerInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);
        responsePage.setList(resultList);

        return responseData(BaseResponse.success("查询所有消费记录成功", responsePage));
    }

    @RequestMapping("/addConsumerInfo")
    @ResponseBody
    @ApiOperation(value= "新增消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> addConsumerInfo(@RequestBody BaseRequest<AddConsumerInfoRequest> baseRequest){
        LOGGER.info("新增消费消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        AddConsumerInfoRequest addConsumerInfoRequest = baseRequest.getParam();
        ConsoleConsumerInfo info = new ConsoleConsumerInfo();
        BeanUtils.copyProperties(addConsumerInfoRequest, info);

        String consumerTime = addConsumerInfoRequest.getConsumerTime();
        consumerTime = consumerTime + ":00";
        consumerTime = consumerTime.replace("T", " ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            info.setConsumerTime(sdf.parse(consumerTime));
        } catch (ParseException e) {
            LOGGER.info("消费时间格式化失败", JSON.toJSONString(consumerTime));
            e.printStackTrace();
        }

        boolean isSuccess = consoleConsumerInfoService.addConsumerInfo(info);

        if(isSuccess){
            info.setPath(Constants.CONSUMER_VIEW);
            return responseData(BaseResponse.success("新增消费消费记录成功", info));
        }else{
            return responseData(BaseResponse.fail("新增消费消费记录失败"));
        }
    }

    @RequestMapping("/editConsumerInfo")
    @ResponseBody
    @ApiOperation(value= "编辑消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> editConsumerInfo(@RequestBody BaseRequest<EditConsumerInfoRequest> baseRequest){
        LOGGER.info("编辑消费消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        EditConsumerInfoRequest editConsumerInfoRequest = baseRequest.getParam();
        ConsoleConsumerInfo info = new ConsoleConsumerInfo();
        BeanUtils.copyProperties(editConsumerInfoRequest, info);

        boolean isSuccess = consoleConsumerInfoService.editConsumerInfo(info);

        if(isSuccess){
            info.setPath(Constants.CONSUMER_VIEW);
            return responseData(BaseResponse.success("编辑消费消费记录成功", info));
        }else{
            return responseData(BaseResponse.fail("编辑消费消费记录失败"));
        }
    }

    @RequestMapping("/deleteConsumerInfo")
    @ResponseBody
    @ApiOperation(value= "删除消费记录接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> deleteConsumerInfo(@RequestBody BaseRequest<DeleteConsumerInfoRequest> baseRequest){
        LOGGER.info("删除消费消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        DeleteConsumerInfoRequest deleteConsumerInfoRequest = baseRequest.getParam();
        Integer id = deleteConsumerInfoRequest.getId();

        int i = consoleConsumerInfoService.deleteConsumerInfo(id);

        if(i > 0){
            return responseData(BaseResponse.success("删除消费消费记录成功", Constants.CONSUMER_VIEW));
        }else{
            return responseData(BaseResponse.fail("删除消费消费记录失败"));
        }
    }

    @RequestMapping("/statisticsConsumerData")
    @ResponseBody
    @ApiOperation(value = "统计消费记录接口", httpMethod = "POST" , response = BaseResponse.class)
    private ResponseEntity<String> statisticsConsumerData(@RequestBody BaseRequest<StatisticsConsumerDataRequest> baseRequest) throws ParseException {
        LOGGER.info("统计消费记录请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        if(baseRequest.getParam().getCategory() == 5){
            String queryTimeStart = baseRequest.getParam().getQueryTimeStart();
            String queryTimeEnd = baseRequest.getParam().getQueryTimeEnd();
            // 判断时间间隔是否超过15天
            if(!StringUtils.isEmpty(queryTimeStart) && !StringUtils.isEmpty(queryTimeEnd)){
                baseRequest.getParam().setQueryTimeStart(queryTimeStart + " 00:00:00");
                baseRequest.getParam().setQueryTimeEnd(queryTimeEnd + " 23:59:59");
                Long days = DateUtil.betweenDays(baseRequest.getParam().getQueryTimeStart(), baseRequest.getParam().getQueryTimeEnd());
                if(days < 0){
                    return responseData(BaseResponse.fail("结束时间不能大于开始时间!"));
                }
                if(days > 30){
                    return responseData(BaseResponse.fail("时间间隔不大于15天!"));
                }
            }
            if((!StringUtils.isEmpty(queryTimeStart) && StringUtils.isEmpty(queryTimeEnd))
                    || (StringUtils.isEmpty(queryTimeStart) && !StringUtils.isEmpty(queryTimeEnd))){
                return responseData(BaseResponse.fail("查询时间不可为空!"));
            }
        }else if(baseRequest.getParam().getCategory() == 6){
            String queryTimeStart = baseRequest.getParam().getQueryTimeStart();
            if(!StringUtils.isEmpty(queryTimeStart)){
                baseRequest.getParam().setQueryTimeStart(queryTimeStart + " 00:00:00");
                baseRequest.getParam().setQueryTimeEnd(queryTimeStart + " 23:59:59");
            }
        }

        StatisticsConsumerDataResponse response = this.consoleConsumerInfoService.statisticsConsumerData(baseRequest.getParam());
        response.setCategory(baseRequest.getParam().getCategory());

        return responseData(BaseResponse.success("统计消费记录成功!", response));
    }

}
