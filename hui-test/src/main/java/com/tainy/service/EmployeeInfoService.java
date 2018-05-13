package com.tainy.service;

import com.tainy.entity.EmployeeInfo;
import com.tainy.util.page.Page;

import java.util.List;

/**
 * Employee服务层接口
 * @author Tainy
 * @date 2017/9/27
 */
public interface EmployeeInfoService {

    /**
     * 获得所有员工
     *
     * @return
     */
    List<EmployeeInfo> listAllEmployeeInfo(Page<?> page, EmployeeInfo employeeInfo);

    /**
     * 新增员工
     *
     * @param employeeInfo
     * @return
     */
    int addEmployeeInfo(EmployeeInfo employeeInfo);

    /**
     * 获得员工
     *
     * @return
     */
    List<EmployeeInfo> listEmployeeInfoByObject(EmployeeInfo employeeInfo);

    /**
     * 编辑员工
     *
     * @param employeeInfo
     * @return
     */
    int editEmployeeInfo(EmployeeInfo employeeInfo);

    /**
     * 删除员工
     *
     * @param employeeInfo
     * @return
     */
    int deleteEmployeeInfo(EmployeeInfo employeeInfo);
}
