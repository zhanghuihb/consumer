package com.tainy.common.base;

import com.alibaba.fastjson.JSON;
import com.tainy.common.exception.TokenLossEfficacyException;
import com.tainy.common.util.page.Page;
import com.tainy.common.util.vo.page.PageRequest;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

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

	@ExceptionHandler({ TokenLossEfficacyException.class })
	@ResponseBody
	public ResponseEntity<String> handleTokenLossEfficacyException(TokenLossEfficacyException e) {
		HttpHeaders respHeader = new HttpHeaders();
		respHeader.set("Content-Type", "application/json;charset=UTF-8");

		LOGGER.warn(ResultCodeMsgEnum.USER_FAIL.getShowMsg(), e);

		BaseResponse<?> baseResponse = BaseResponse.fail(ResultCodeMsgEnum.USER_FAIL.getCode(),ResultCodeMsgEnum.USER_FAIL.getMsg());

		return new ResponseEntity<>(JSON.toJSONString(baseResponse), respHeader, HttpStatus.OK);
	}
	/**
	 * excel导出视图内部类
	 */
	public static class JxlsExcelView extends AbstractView {

		private static final Logger LOGGER = LoggerFactory.getLogger(JxlsExcelView.class);

		private String templatePath;
		private String exportFileName;

		/**
		 * @param templatePath   模版相对于当前classpath路径
		 * @param exportFileName 导出文件名
		 */
		public JxlsExcelView(String templatePath, String exportFileName) {
			if (exportFileName != null) {
				try {
					exportFileName = URLEncoder.encode(exportFileName, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					LOGGER.error("URLEncoder.encode(exportFileName, 'UTF-8') exception", e);
				}
			}
			this.exportFileName = exportFileName;
			this.templatePath = templatePath;
			super.setContentType("application/vnd.ms-excel");
		}

		@Override
		protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
											   HttpServletResponse response) throws Exception {
			Context context = new Context(model);
			response.setContentType(super.getContentType());
			response.setHeader("content-disposition", "attachment;filename=" + exportFileName + ".xlsx");
			ServletOutputStream os = response.getOutputStream();
			InputStream is = this.getClass().getResourceAsStream(templatePath);
			JxlsHelper.getInstance().processTemplate(is, os, context);
			is.close();
		}

	}
}