package com.tainy.service.impl;

import com.tainy.entity.DeptInfo;
import com.tainy.entity.UserInfo;
import com.tainy.mapper.DeptInfoMapper;
import com.tainy.mapper.UserInfoMapper;
import com.tainy.service.DeptInfoService;
import com.tainy.service.UserInfoService;
import com.tainy.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DeptInfo服务接口实现类
 * @Service("deptInfoService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/9/27
 */
@Service
public class DeptInfoServiceImpl implements DeptInfoService {

    @Autowired
    private DeptInfoMapper deptInfoMapper;

    @Override
    public List<DeptInfo> listAllDeptInfo(Page<?> page, DeptInfo deptInfo) {
        return deptInfoMapper.selectByPageListAllDeptInfo(page, deptInfo);
    }

    @Override
    public int addDeptInfo(DeptInfo deptInfo) {
        return deptInfoMapper.insertSelective(deptInfo);
    }

    @Override
    public List<DeptInfo> listDeptInfoByObject(DeptInfo deptInfo) {
        return deptInfoMapper.listDeptInfoByObject(deptInfo);
    }

    @Override
    public int editDeptInfo(DeptInfo deptInfo) {
        return deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
    }

    @Override
    public int deleteDeptInfo(DeptInfo deptInfo) {
        return deptInfoMapper.deleteByPrimaryKey(deptInfo.getId());
    }
}
