package com.tainy.vo.dept;


import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class DeleteDeptInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "部门ID不能为空")
	@ApiModelProperty(value = "部门ID", required = true)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
