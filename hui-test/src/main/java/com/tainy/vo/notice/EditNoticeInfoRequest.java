package com.tainy.vo.notice;


import com.wordnik.swagger.annotations.ApiModelProperty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;

public class EditNoticeInfoRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "公告ID不能为空")
	@ApiModelProperty(value = "公告ID", required = true)
	private Long id;

	@NotNull(message = "公告标题不能为空")
	@ApiModelProperty(value = "公告标题", required = true)
	private String title;

	@NotNull(message = "公告内容不能为空")
	@ApiModelProperty(value = "公告内容", required = true)
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}
