package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.DeptInfo;
import com.tainy.entity.EmployeeInfo;
import com.tainy.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {


    /**
     * 获得所有员工（分页查询）
     *
     * @return
     */
    List<EmployeeInfo> selectByPageListAllEmployeeInfo(@Param("page") Page<?> page, @Param("employeeInfo") EmployeeInfo employeeInfo);

    /**
     * 获得员工
     *
     * @param employeeInfo
     * @return
     */
    List<EmployeeInfo> listEmployeeInfoByObject(EmployeeInfo employeeInfo);
}