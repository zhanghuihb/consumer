package com.tainy.controller.excel;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Tainy
 * @date 2017/10/18
 */
public class JxlsExcelView extends AbstractView {

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