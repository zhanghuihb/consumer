package com.tainy.console.vo.console.dto;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/2/21
 */
public class StatisticsConsumerDataDTO implements Serializable{

    private Integer amount;

    private Integer digest;

    private String city;

    private String codeName;

    private Integer sheets;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDigest() {
        return digest;
    }

    public void setDigest(Integer digest) {
        this.digest = digest;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getSheets() {
        return sheets;
    }

    public void setSheets(Integer sheets) {
        this.sheets = sheets;
    }
}
