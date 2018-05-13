package com.tainy.console.vo.console.vo;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/3/25
 */
public class AccountDataVO implements Serializable{

    private String name;

    private Double value;

    private Integer sheets = 0;

    public AccountDataVO(){

    }

    public AccountDataVO(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getSheets() {
        return sheets;
    }

    public void setSheets(Integer sheets) {
        this.sheets = sheets;
    }
}
