package com.tainy.vo.job;


import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class EditJobInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "职位ID不能为空")
	@ApiModelProperty(value = "职位ID", required = true)
	private Long id;

	@NotNull(message = "职位名称不能为空")
	@ApiModelProperty(value = "职位名称", required = true)
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
