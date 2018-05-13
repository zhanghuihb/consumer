package com.tainy.mapper;

import com.tainy.common.BaseMapper;
import com.tainy.entity.DeptInfo;
import com.tainy.util.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptInfoMapper extends BaseMapper<DeptInfo>{

    /**
     * 获得所有部门（分页查询）
     *
     * @return
     */
    List<DeptInfo> selectByPageListAllDeptInfo(@Param("page") Page<?> page, @Param("deptInfo") DeptInfo deptInfo);

    /**
     * 获得部门
     *
     * @param deptInfo
     * @return
     */
    List<DeptInfo> listDeptInfoByObject(DeptInfo deptInfo);
}