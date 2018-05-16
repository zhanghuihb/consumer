package com.tainy.console.controller;

import com.alibaba.fastjson.JSON;
import com.tainy.common.base.BaseController;
import com.tainy.common.base.BaseRequest;
import com.tainy.common.base.BaseResponse;
import com.tainy.common.base.Constant;
import com.tainy.common.domain.console.ConsoleHotel;
import com.tainy.common.util.StringUtil;
import com.tainy.common.util.page.Page;
import com.tainy.console.service.ConsoleHotelService;
import com.tainy.console.vo.console.GetHotelsRequest;
import com.tainy.console.vo.console.StatisticsResponse;
import com.tainy.console.vo.console.vo.StatisticsAccordingAreaVO;
import com.tainy.console.vo.console.vo.StatisticsAccordingScoreVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/12
 */
@Controller
@RequestMapping("/v1/consoleHotel")
public class ConsoleHotelController extends BaseController{

    @Autowired
    private ConsoleHotelService consoleHotelService;

    @RequestMapping("/getAllHotels")
    @ResponseBody
    @ApiOperation(value = "查询所有酒店接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getAllHotels(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("查询所有酒店请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();
        ConsoleHotel hotel = new ConsoleHotel();
        hotel.setDelFlag(Constant.DEL_FLAG_NO);

        List<ConsoleHotel> resultList = consoleHotelService.getAllHotels(hotel);

        return responseData(BaseResponse.success("查询所有酒店成功", resultList));
    }

    @RequestMapping("/getHotels")
    @ResponseBody
    @ApiOperation(value = "查询酒店接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> getHotels(@RequestBody BaseRequest<GetHotelsRequest> baseRequest){
        LOGGER.info("查询酒店请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        String queryName = baseRequest.getParam().getQueryName();
        String queryCity = baseRequest.getParam().getQueryCity();
        String queryAddress = baseRequest.getParam().getQueryAddress();
        ConsoleHotel hotel = new ConsoleHotel();
        if(!StringUtil.isEmpty(queryName)){
            hotel.setName(queryName);
        }if(!StringUtil.isEmpty(queryCity)){
            hotel.setCity(queryCity);
        }
        if(!StringUtil.isEmpty(queryAddress)){
            hotel.setAddress(queryAddress);
        }

        hotel.setDelFlag(Constant.DEL_FLAG_NO);

        Long startTime = System.currentTimeMillis();
        List<ConsoleHotel> resultList = consoleHotelService.selectListPageGetHotels(page, hotel);
        System.out.println("查询酒店耗时：" + (System.currentTimeMillis() - startTime));
        Page<ConsoleHotel> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);
        responsePage.setList(resultList);

        return responseData(BaseResponse.success("查询酒店成功", responsePage));
    }

    @RequestMapping("/statistics")
    @ResponseBody
    @ApiOperation(value = "统计接口", httpMethod = "POST", response = BaseResponse.class)
    public ResponseEntity<String> statistics(@RequestBody BaseRequest<?> baseRequest){
        LOGGER.info("统计请求数据 baseRequest{}", JSON.toJSONString(baseRequest));
        baseRequest.validate();
        Long startTime = System.currentTimeMillis();
        //区域
        List<StatisticsAccordingAreaVO> areaList = consoleHotelService.statisticsAccordingArea();
        //评分
        List<StatisticsAccordingScoreVO> scoreList = consoleHotelService.statisticsAccordingScore();
        StatisticsResponse response = new StatisticsResponse();
        response.setAreaList(areaList);
        response.setScoreList(scoreList);
        System.out.println("区域统计耗时：" + (System.currentTimeMillis() - startTime));
        return responseData(BaseResponse.success("区域统计成功", response));
    }
}
