package com.tainy.console.controller;

import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.util.constant.Constants;
import com.tainy.common.vo.console.QueryShopsRequest;
import com.tainy.console.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tainy
 * @date 2018/8/13 16:35
 */
@RestController
@RequestMapping("/v1/shop")
public class ShopController extends BaseController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/queryShopsById")
    @ResponseBody
    @ApiOperation(value = "根据ID查询店铺接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> queryShopsById(@RequestBody BaseRequest<Integer> baseRequest){
        return responseData(BaseResponse.success(shopService.queryShopsById(baseRequest.getParam())));
    }

    @RequestMapping("/queryShops")
    @ResponseBody
    @ApiOperation(value = "查询店铺接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> queryShops(@RequestBody BaseRequest<QueryShopsRequest> baseRequest){
        return responseData(BaseResponse.success(shopService.queryShops(baseRequest.getParam())));
    }
}
