package com.tainy.vo.user;


import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class EditUserInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户ID不能为空")
	@ApiModelProperty(value = "用户ID", required = true)
	private Long id;

	@NotNull(message = "登录名不能为空")
	@ApiModelProperty(value = "登录名", required = true)
	private String userName;

	@NotNull(message = "用户状态不能为空")
	@ApiModelProperty(value = "用户状态", required = true)
	private Short status;

	@NotNull(message = "用户组织部门ID不能为空")
	@ApiModelProperty(value = "用户组织部门ID", required = true)
	private Long organizationId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
}
