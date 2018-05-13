package com.tainy.vo.dept;


import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class EditDeptInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "部门ID不能为空")
	@ApiModelProperty(value = "部门ID", required = true)
	private Long id;

	@NotNull(message = "部门名称不能为空")
	@ApiModelProperty(value = "部门名称", required = true)
	private String name;

	@NotNull(message = "详细描述不能为空")
	@ApiModelProperty(value = "详细描述", required = true)
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}
