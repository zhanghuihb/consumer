package com.tainy.service;

import com.tainy.entity.DeptInfo;
import com.tainy.entity.UserInfo;
import com.tainy.util.page.Page;

import java.util.List;

/**
 * Dept服务层接口
 * @author Tainy
 * @date 2017/9/27
 */
public interface DeptInfoService {

    /**
     * 获得所有部门
     *
     * @return
     */
    List<DeptInfo> listAllDeptInfo(Page<?> page, DeptInfo deptInfo);

    /**
     * 新增部门
     *
     * @param deptInfo
     * @return
     */
    int addDeptInfo(DeptInfo deptInfo);

    /**
     * 获得部门
     *
     * @return
     */
    List<DeptInfo> listDeptInfoByObject(DeptInfo deptInfo);

    /**
     * 编辑部门
     *
     * @param deptInfo
     * @return
     */
    int editDeptInfo(DeptInfo deptInfo);

    /**
     * 删除部门
     *
     * @param deptInfo
     * @return
     */
    int deleteDeptInfo(DeptInfo deptInfo);
}
