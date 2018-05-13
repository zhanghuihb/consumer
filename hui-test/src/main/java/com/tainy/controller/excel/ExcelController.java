package com.tainy.controller.excel;

import com.tainy.common.BaseController;
import com.tainy.entity.JobInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 导出Excel
 * @author Tainy
 * @date 2017/10/18
 */
@Controller
@RequestMapping(value = "/api/excel/v1")
public class ExcelController extends BaseController {

    /**
     * /api/excel/v1/jobExcel
     * 数据导出
     * @return
     */
    @RequestMapping(value = "/jobExcel")
    public ModelAndView exportExcel() {
        List<JobInfo> jobInfoList = new ArrayList<>();
        JobInfo jobInfo = new JobInfo();
        jobInfo.setName("Java开发工程师");
        jobInfo.setRemark("Java开发工程师");

        jobInfoList.add(jobInfo);

        Map<String, Object> model = new HashMap<>();
        model.put("jobInfoList", jobInfoList);
        model.put("name", "晶汉科技");

        return new ModelAndView(new JxlsExcelView("/template/JobInfo.xls", "output"), model);
    }

}
