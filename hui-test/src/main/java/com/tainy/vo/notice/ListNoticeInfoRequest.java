package com.tainy.vo.notice;


import io.swagger.annotations.ApiModelProperty;
import com.tainy.vo.page.PageRequest;

import java.io.Serializable;

public class ListNoticeInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "公告标题", required = true)
	private String title;

	@ApiModelProperty(value = "公告内容", required = true)
	private String content;

	@ApiModelProperty(value = "分页参数", required = true)
	private PageRequest.Page page;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public PageRequest.Page getPage() {
		return page;
	}

	public void setPage(PageRequest.Page page) {
		this.page = page;
	}
}
