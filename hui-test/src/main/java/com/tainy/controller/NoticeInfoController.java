package com.tainy.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import com.tainy.common.BaseController;
import com.tainy.common.BaseRequest;
import com.tainy.common.BaseResponse;
import com.tainy.common.Constant;
import com.tainy.entity.NoticeInfo;
import com.tainy.service.NoticeInfoService;
import com.tainy.util.page.Page;
import com.tainy.vo.notice.AddNoticeInfoRequest;
import com.tainy.vo.notice.DeleteNoticeInfoRequest;
import com.tainy.vo.notice.EditNoticeInfoRequest;
import com.tainy.vo.notice.ListNoticeInfoRequest;
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

import java.util.Date;
import java.util.List;

/**
 * 处理公告请求控制器
 *
 * @author Tainy
 * @date 2017/11/27
 */
@Controller
@RequestMapping("/v1/noticeInfo")
public class NoticeInfoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeInfoController.class);

    @Autowired
    private NoticeInfoService noticeInfoService;

    @RequestMapping(value = "/listAllNoticeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "查询所有公告接口", httpMethod = "POST", response = BaseResponse.class, notes = "查询所有公告")
    public ResponseEntity<String> listAllNoticeInfo(@RequestBody BaseRequest<ListNoticeInfoRequest> baseRequest){
        LOGGER.info("查询所有公告请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        Page<?> page = setPage(baseRequest.getParam().getPage());

        ListNoticeInfoRequest listNoticeInfoRequest = baseRequest.getParam();
        NoticeInfo noticeInfo = new NoticeInfo();
        BeanUtils.copyProperties(listNoticeInfoRequest, noticeInfo);

        List<NoticeInfo> resutList = noticeInfoService.listAllNoticeInfo(page, noticeInfo);

        Page<NoticeInfo> responsePage = super.setPage(baseRequest.getParam().getPage());
        BeanUtils.copyProperties(page, responsePage);

        if(!CollectionUtils.isEmpty(resutList)){
            responsePage.setList(resutList);
            return responseData(BaseResponse.success("查询所有公告成功", responsePage));
        }else{
            return responseData(BaseResponse.success("查询所有公告为空", responsePage));
        }
    }

    @RequestMapping(value = "/addNoticeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "新增公告接口", httpMethod = "POST", response = BaseResponse.class, notes = "新增公告")
    public ResponseEntity<String> addUserInfo(@RequestBody BaseRequest<AddNoticeInfoRequest> baseRequest){
        LOGGER.info("新增公告请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        AddNoticeInfoRequest addNoticeInfoRequest = baseRequest.getParam();

        NoticeInfo noticeInfo = new NoticeInfo();
        BeanUtils.copyProperties(addNoticeInfoRequest, noticeInfo);

        Date now = new Date();
        noticeInfo.setCreateTime(now);
        noticeInfo.setDelFlag(Constant.DEL_FLAG_NO);

        int i = noticeInfoService.addNoticeInfo(noticeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("新增公告成功", noticeInfo));
        }else{
            return responseData(BaseResponse.fail("新增公告失败"));
        }
    }

    @RequestMapping(value = "/editNoticeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "编辑公告接口", httpMethod = "POST", response = BaseResponse.class, notes = "编辑公告")
    public ResponseEntity<String> editNoticeInfo(@RequestBody BaseRequest<EditNoticeInfoRequest> baseRequest){
        LOGGER.info("编辑公告请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        EditNoticeInfoRequest editNoticeInfoRequest = baseRequest.getParam();

        NoticeInfo noticeInfo = new NoticeInfo();
        BeanUtils.copyProperties(editNoticeInfoRequest, noticeInfo);

        int i = noticeInfoService.editNoticeInfo(noticeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("编辑公告成功", noticeInfo));
        }else{
            return responseData(BaseResponse.fail("编辑公告失败"));
        }
    }

    @RequestMapping(value = "/deleteNoticeInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "删除公告接口", httpMethod = "POST", response = BaseResponse.class, notes = "删除公告")
    public ResponseEntity<String> deleteNoticeInfo(@RequestBody BaseRequest<DeleteNoticeInfoRequest> baseRequest){
        LOGGER.info("删除公告请求数据：" + JSON.toJSONString(baseRequest));

        baseRequest.validate();

        DeleteNoticeInfoRequest deleteNoticeInfoRequest = baseRequest.getParam();

        NoticeInfo noticeInfo = new NoticeInfo();
        BeanUtils.copyProperties(deleteNoticeInfoRequest, noticeInfo);

        int i = noticeInfoService.deleteNoticeInfo(noticeInfo);

        if(i > 0){
            return responseData(BaseResponse.success("删除公告成功"));
        }else{
            return responseData(BaseResponse.fail("删除公告失败"));
        }
    }
}
