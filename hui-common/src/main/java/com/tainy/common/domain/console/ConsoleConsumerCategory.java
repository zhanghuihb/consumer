package com.tainy.common.domain.console;

import com.tainy.common.domain.BaseBean;

import java.util.List;

public class ConsoleConsumerCategory extends BaseBean {

    private String code;

    private String name;

    private Integer status;

    private String parentCode;

    private List<ConsoleConsumerCategory> codeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode == null ? null : parentCode.trim();
    }

    public List<ConsoleConsumerCategory> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<ConsoleConsumerCategory> codeList) {
        this.codeList = codeList;
    }
}