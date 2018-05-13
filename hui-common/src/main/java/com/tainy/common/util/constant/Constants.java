package com.tainy.common.util.constant;

import com.tainy.common.util.prop.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {

	private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

	/***************** system *****************/
	public static final String CLASSPATH = Constants.class.getResource("//").getFile();
	public static String DB_NAME = "";

	/***************** default password (123456) *****************/
	public static final String DEFAULT_PASSWORD = "14e1b600b1fd579f47433b88e8d85291";

	/***************** session key *****************/
	public static final String CURRENT_USER = "CURRENT_USER";
	public static final String ENTER_COUNT = "ENTER_COUNT";
	public static final String NEED_CAPTCHA = "NEED_CAPTCHA";

	/***************** page *****************/
	public static final String LOGIN_PAGE = "jsp/login.jsp";
	public static final String INDEX_PAGE = "jsp/frame/index.jsp";
	public static final String EXCEPTION_PAGE = "jsp/error/exception.jsp";
	public static final String MAIN_PAGE = "jsp/frame/main.jsp";

	/***************** redis key *****************/
	public static final String MENU = "menu";
	public static final String MENU_TOP = "menuTop";
	public static final String MENU_MAIN = "menuMain";

	/***************** ueditor upload path *****************/
	public static final String IMAGE_PATH = "/upload/image/";
	public static final String FILE_PATH = "/upload/file/";

	public static final String PORTRAIT_PATH = "/upload/portrait/";

	static {
		String driverClassName = PropertiesUtils.getProperty("/jdbc.properties", "database.driverClassName");
		if ("com.microsoft.sqlserver.jdbc.SQLServerDriver".equals(driverClassName)) {
			Constants.DB_NAME = "sqlserver";
		} else if ("oracle.jdbc.driver.OracleDriver".equals(driverClassName)) {
			Constants.DB_NAME = "oracle";
		} else if ("com.mysql.jdbc.Driver".equals(driverClassName)) {
			Constants.DB_NAME = "mysql";
		}
		LOGGER.info("DB_NAME - {}", Constants.DB_NAME);
	}

	/***************** system *****************/
	public static final String RESOURCE_VIEW = "consoleResource.jsp";
	public static final String ROLE_VIEW = "consoleRole.jsp";
	public static final String USER_VIEW = "consoleUser.jsp";
	public static final String CONSUMER_VIEW = "consoleConsumerInfo.jsp";
}
