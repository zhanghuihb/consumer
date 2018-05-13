package com.tainy.service.impl;

import com.tainy.entity.EmployeeInfo;
import com.tainy.mapper.EmployeeInfoMapper;
import com.tainy.service.EmployeeInfoService;
import com.tainy.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * EmployeeInfo服务接口实现类
 * @Service("mployeeInfoService")用于将当前类注释为一个Spring的bean，名为Service
 *
 * @author Tainy
 * @date 2017/9/27
 */
@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    @Override
    public List<EmployeeInfo> listAllEmployeeInfo(Page<?> page, EmployeeInfo employeeInfo) {
        return employeeInfoMapper.selectByPageListAllEmployeeInfo(page, employeeInfo);
    }

    @Override
    public int addEmployeeInfo(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.insertSelective(employeeInfo);
    }

    @Override
    public List<EmployeeInfo> listEmployeeInfoByObject(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.listEmployeeInfoByObject(employeeInfo);
    }

    @Override
    public int editEmployeeInfo(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.updateByPrimaryKeySelective(employeeInfo);
    }

    @Override
    public int deleteEmployeeInfo(EmployeeInfo employeeInfo) {
        return employeeInfoMapper.deleteByPrimaryKey(employeeInfo.getId());
    }
}
