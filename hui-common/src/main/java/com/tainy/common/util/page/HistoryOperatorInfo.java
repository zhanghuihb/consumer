package com.tainy.common.util.page;

public class HistoryOperatorInfo {

	private Integer bizType;

	private Integer operator;

	private Integer operatorType;

	private String tableName;

	private String tableNameHis;

	private String primaryKeyColumnName;

	private Integer soDoneCode;

	private Integer id;

	private boolean isInsert = false;

	public Integer getOperator() {
		return operator;
	}

	public void setOperator(Integer operator) {
		this.operator = operator;
	}

	public Integer getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(Integer operatorType) {
		this.operatorType = operatorType;
	}

	public String getTableNameHis() {
		return tableNameHis;
	}

	public void setTableNameHis(String tableNameHis) {
		this.tableNameHis = tableNameHis;
	}

	public String getPrimaryKeyColumnName() {
		return primaryKeyColumnName;
	}

	public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
		this.primaryKeyColumnName = primaryKeyColumnName;
	}

	public Integer getSoDoneCode() {
		return soDoneCode;
	}

	public void setSoDoneCode(Integer soDoneCode) {
		this.soDoneCode = soDoneCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isInsert() {
		return isInsert;
	}

	public void setInsert(boolean isInsert) {
		this.isInsert = isInsert;
	}

}
