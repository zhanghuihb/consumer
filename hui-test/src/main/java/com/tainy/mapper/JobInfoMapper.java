package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.JobInfo;
import com.tainy.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobInfoMapper extends BaseMapper<JobInfo> {


    /**
     * 获得所有部门（分页查询）
     *
     * @return
     */
    List<JobInfo> selectByPageListAllJobInfo(@Param("page") Page<?> page, @Param("jobInfo") JobInfo jobInfo);

    /**
     * 获得用户
     *
     * @param jobInfo
     * @return
     */
    List<JobInfo> listJobInfoByObject(JobInfo jobInfo);

}
