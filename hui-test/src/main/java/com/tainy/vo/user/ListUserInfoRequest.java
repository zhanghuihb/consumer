package com.tainy.vo.user;


import io.swagger.annotations.ApiModelProperty;
import com.tainy.vo.page.PageRequest;

import java.io.Serializable;

public class ListUserInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户名", required = true)
	private String userName;

	@ApiModelProperty(value = "分页参数", required = true)
	private PageRequest.Page page;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public PageRequest.Page getPage() {
		return page;
	}

	public void setPage(PageRequest.Page page) {
		this.page = page;
	}
}
