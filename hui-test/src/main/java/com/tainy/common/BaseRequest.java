package com.tainy.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 请求参数协议基类。包括协议头和上行参数类。
 * 
 * @author Silence
 *
 * @param <Param>
 *            参数实体类
 */
@ApiModel(value = "请求基类", description = "请求基类")
public class BaseRequest<Param> {

	@ApiModelProperty(value = "请求Id", required = false)
	protected Long reqId;

	@ApiModelProperty(value = "渠道号，参考 ChannelEnum", required = false)
	protected Short channel;

	@ApiModelProperty(value = "操作系统", required = false)
	protected String os;

	@ApiModelProperty(value = "协议版本", required = false)
	protected String ver;

	@ApiModelProperty(value = "APP版本", required = false)
	protected String appVer;

	@ApiModelProperty(value = "机型", required = false)
	protected String model;

	@ApiModelProperty(value = "经度", required = false)
	protected String lng;

	@ApiModelProperty(value = "维度", required = false)
	protected String lat;

	@ApiModelProperty(value = "签名类型", required = false)
	protected String signType = "RSA";

	@ApiModelProperty(value = "签名", required = false)
	protected String sign;

	@ApiModelProperty(value = "请求令牌", required = false)
	protected String token;

	@ApiModelProperty(value = "参数实体类", required = false)
	protected Param param;

	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public Short getChannel() {
		return channel;
	}

	public void setChannel(Short channel) {
		this.channel = channel;
	}

	/**
	 * 参数验证。 当上行参数为 null 或 参数格式非法时抛出 IllegalArgumentException 。
	 * 
	 * @return true 验证成功； false 验证失败
	 */
	public boolean validate() {		
		List<ConstraintViolation> errList = new Validator().validate(param);
		for (ConstraintViolation err : errList) {
			throw new IllegalArgumentException("[" + err.getCheckName() + "-" + err.getMessage() + "]");
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BaseRequest<?> b = new BaseRequest<Void>();
		System.out.println(b.getClass().getTypeParameters()[0].getClass());

		Constructor<Void> cv = Void.class.getDeclaredConstructor();
		cv.setAccessible(true);
		Void v = cv.newInstance();
		System.out.println(v instanceof Void); // -> true
	}

}