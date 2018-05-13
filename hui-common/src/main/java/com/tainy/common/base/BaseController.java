package com.tainy.common.base;

import com.alibaba.fastjson.JSON;
import com.tainy.common.util.page.Page;
import com.tainy.common.util.vo.page.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {

	}

	protected ResponseEntity<String> responseData(BaseResponse<?> baseResponse) {

		HttpHeaders respHeader = new HttpHeaders();
		respHeader.set("Content-Type", "application/json;charset=UTF-8");
		String repJson = JSON.toJSONString(baseResponse);
		String msg = baseResponse.getData() == null ? "" : baseResponse.getData().getClass().getName();
		LOGGER.info("baseResponse: " + msg + " " + repJson);

		return new ResponseEntity<String>(repJson, respHeader, HttpStatus.OK);
	}
	protected <T> Page<T> setPage(PageRequest.Page page) {

		Page<T> pagePlugin = new Page<T>();

		if (page == null) {
			pagePlugin.setCurrentPage(1);
			pagePlugin.setPageSize(20);
		} else {
			BeanUtils.copyProperties(page, pagePlugin);
			// 判断传递参数
			if (page.getCurrentPage() == 0) {
				pagePlugin.setCurrentPage(1);
			}

			if (page.getPageSize() == 0) {
				pagePlugin.setPageSize(20);
			}
		}

		pagePlugin.setShowCount(pagePlugin.getPageSize());

		return pagePlugin;
	}
}