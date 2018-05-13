package com.tainy.service;

import com.tainy.entity.JobInfo;
import com.tainy.util.page.Page;

import java.util.List;

/**
 * Job服务层接口
 * @author Tainy
 * @date 2017/9/27
 */
public interface JobInfoService {

    /**
     * 获得所有职位
     *
     * @return
     */
    List<JobInfo> listAllJobInfo(Page<?> page, JobInfo jobInfo);

    /**
     * 新增职位
     *
     * @param jobInfo
     * @return
     */
    int addJobInfo(JobInfo jobInfo);

    /**
     * 获得职位
     *
     * @return
     */
    List<JobInfo> listJobInfoByObject(JobInfo jobInfo);

    /**
     * 编辑职位
     *
     * @param jobInfo
     * @return
     */
    int editJobInfo(JobInfo jobInfo);

    /**
     * 删除职位
     *
     * @param jobInfo
     * @return
     */
    int deleteJobInfo(JobInfo jobInfo);
}
