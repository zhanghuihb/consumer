package com.tainy.vo.init;

import com.tainy.entity.DeptInfo;
import com.tainy.entity.JobInfo;
import com.tainy.entity.Organization;

import java.io.Serializable;
import java.util.List;

public class InitDataResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Organization> organizationList;

	private List<DeptInfo> deptInfoList;

	private List<JobInfo> jobInfoList;

	public List<Organization> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<Organization> organizationList) {
		this.organizationList = organizationList;
	}

	public List<DeptInfo> getDeptInfoList() {
		return deptInfoList;
	}

	public void setDeptInfoList(List<DeptInfo> deptInfoList) {
		this.deptInfoList = deptInfoList;
	}

	public List<JobInfo> getJobInfoList() {
		return jobInfoList;
	}

	public void setJobInfoList(List<JobInfo> jobInfoList) {
		this.jobInfoList = jobInfoList;
	}
}
