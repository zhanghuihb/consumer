package com.tainy.util.vo;

public class CommonContext {

	private Integer soDoneCode;

	private Integer orderId;

	private boolean isNeedSaveHis = false;

	private Integer bizType;

	public Integer getSoDoneCode() {
		return soDoneCode;
	}

	public void setSoDoneCode(Integer soDoneCode) {
		this.soDoneCode = soDoneCode;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public boolean isNeedSaveHis() {
		return isNeedSaveHis;
	}

	public void setNeedSaveHis(boolean isNeedSaveHis) {
		this.isNeedSaveHis = isNeedSaveHis;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

}
