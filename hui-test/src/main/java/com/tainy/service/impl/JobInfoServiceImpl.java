package com.tainy.service.impl;

import com.tainy.entity.JobInfo;
import com.tainy.mapper.JobInfoMapper;
import com.tainy.service.JobInfoService;
import com.tainy.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JobInfo服务接口实现类
 * @Service("jobInfoService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/9/27
 */
@Service
public class JobInfoServiceImpl implements JobInfoService {

    @Autowired
    private JobInfoMapper jobInfoMapper;

    @Override
    public List<JobInfo> listAllJobInfo(Page<?> page, JobInfo JobInfo) {
        return jobInfoMapper.selectByPageListAllJobInfo(page, JobInfo);
    }

    @Override
    public int addJobInfo(JobInfo JobInfo) {
        return jobInfoMapper.insertSelective(JobInfo);
    }

    @Override
    public List<JobInfo> listJobInfoByObject(JobInfo JobInfo) {
        return jobInfoMapper.listJobInfoByObject(JobInfo);
    }

    @Override
    public int editJobInfo(JobInfo JobInfo) {
        return jobInfoMapper.updateByPrimaryKeySelective(JobInfo);
    }

    @Override
    public int deleteJobInfo(JobInfo JobInfo) {
        return jobInfoMapper.deleteByPrimaryKey(JobInfo.getId());
    }
}
