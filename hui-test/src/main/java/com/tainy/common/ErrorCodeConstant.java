package com.tainy.common;


/**
 * 系统错误常量类
 * 
 * @Description
 * @author Tainy
 * @date 2017年04月10日
 */

public class ErrorCodeConstant {

	public static final int RESULT_CODE_SUCCESS = 0;
	public static final int RESULT_CODE_ERR = 1;

	public static final int ERR_CODE_EXCEPTION = 2; // 未处理异常

	public static final int ERR_CODE_PARAM_EMPTY = 600; // 上行参数（对象）为空
	public static final int ERR_CODE_PARAM_MISSING_OR_PARAM_INVALID = 601; // 缺少必填参数或参数非法
	public static final int ERR_CODE_PARAM_MISSING = 602; // 缺少必填参数
	public static final int ERR_CODE_EXIST = 1000; // 名称已经存在
	

}
