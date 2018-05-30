package com.tainy.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.ParameterizedType;

/**
 * HTTP下行返回请求响应实体类的基类，包括响应头部和响应数据。
 * 
 * @author Silence(王涛)
 *
 * @param <Data>
 */
@ApiModel(value = "响应基类", description = "响应基类")
public class BaseResponse<Data> {

	@ApiModelProperty(value = "响应结果码：0-成功     非0-失败", required = true)
	protected int code; // 
	
	@ApiModelProperty(value = "响应结果描述", required = false)
	protected String msg;
	
	@ApiModelProperty(value = "前端显示响应结果描述", required = false)
	protected String showMsg;
	
	@ApiModelProperty(value = "请求令牌", required = false)
	protected String token;
	
	@ApiModelProperty(value = "响应数据", required = false)
	protected Data data;

	public BaseResponse() {
		try {
			ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
			Class<Data> clazz = (Class<Data>) parameterizedType.getActualTypeArguments()[0];
			data = clazz.newInstance();
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}

	public BaseResponse(int code) {
		super();

		this.code = code;
	}

	public BaseResponse(int code, String msg, String showMsg) {
		this(code);

		this.msg = msg;
		this.showMsg = showMsg;
	}

	public BaseResponse(int code, String msg, String showMsg, Data data) {
		this(code, msg, showMsg);

		this.data = data;
	}

	public static <Data> BaseResponse<Data> success() {
		BaseResponse<Data> response = new BaseResponse<Data>(0, "成功！", "成功！");

		return response;
	}

	public static <Data> BaseResponse<Data> success(Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(0, "成功！", "成功！");
		response.setData(data);

		return response;
	}

	public static <Data> BaseResponse<Data> success(String msg) {
		BaseResponse<Data> response = new BaseResponse<Data>(0, msg, msg);
		response.setData(null);

		return response;
	}

	public static <Data> BaseResponse<Data> success(int code, String msg, Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
		response.setData(data);

		return response;
	}

	public static <Data> BaseResponse<Data> success(String msg, Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(0, msg, msg);
		response.setData(data);

		return response;
	}

	public static <Data> BaseResponse<Data> result(ResultCodeMsgEnum resultCodeMsgEnum) {
		BaseResponse<Data> response = new BaseResponse<Data>(resultCodeMsgEnum.getCode(), resultCodeMsgEnum.getMsg(), resultCodeMsgEnum.getShowMsg());
		
		return response;
	}

	public static <Data> BaseResponse<Data> result(ResultCodeMsgEnum resultCodeMsgEnum, Data data) {
		BaseResponse<Data> response = BaseResponse.result(resultCodeMsgEnum);
		response.setData(data);
		
		return response;
	}

	public static <Data> BaseResponse<Data> fail(String msg) {
		BaseResponse<Data> response = new BaseResponse<Data>(ErrorCodeConstant.RESULT_CODE_ERR, msg, msg);
		response.setData(null);

		return response;
	}

	public static <Data> BaseResponse<Data> fail(int code, Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(code, null, null);
		response.setData(data);

		return response;
	}

	public static <Data> BaseResponse<Data> fail(int code, String msg) {
		BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
		response.setData(null);

		return response;
	}

	public static <Data> BaseResponse<Data> fail(int code, String msg, Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(code, msg, msg);
		response.setData(data);

		return response;
	}

	public static <Data> BaseResponse<Data> fail(int code, String msg, String showMsg, Data data) {
		BaseResponse<Data> response = new BaseResponse<Data>(code, msg, showMsg, data);

		return response;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getShowMsg() {
		return showMsg;
	}

	public void setShowMsg(String showMsg) {
		this.showMsg = showMsg;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String toke) {
		this.token = toke;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
