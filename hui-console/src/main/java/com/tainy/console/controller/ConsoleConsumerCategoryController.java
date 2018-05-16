package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.domain.console.ConsoleConsumerCategory;
import com.tainy.console.service.ConsoleConsumerCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/14
 */
@Controller
@RequestMapping("/v1/consoleConsumerCategory")
public class ConsoleConsumerCategoryController extends BaseController{

    @Autowired
    private ConsoleConsumerCategoryService consoleConsumerCategoryService;

    @RequestMapping("/getAllCodes")
    @ResponseBody
    @ApiOperation(value = "查询所有消费分类接口", httpMethod = "POST", response = BaseResponse.class)
    private ResponseEntity<String> getAllCodes(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询所有消费分类请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        List<ConsoleConsumerCategory> resultList = consoleConsumerCategoryService.getAllCodes();

        return responseData(BaseResponse.success("查询所有消费分类成功", resultList));
    }

}
