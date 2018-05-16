package com.tainy.vo.dept;


import io.swagger.annotations.ApiModelProperty;
import com.tainy.vo.page.PageRequest;

import java.io.Serializable;

public class ListDeptInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "部门名称", required = true)
	private String name;

	@ApiModelProperty(value = "详细描述", required = true)
	private String remark;

	@ApiModelProperty(value = "分页参数", required = true)
	private PageRequest.Page page;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PageRequest.Page getPage() {
		return page;
	}

	public void setPage(PageRequest.Page page) {
		this.page = page;
	}
}
