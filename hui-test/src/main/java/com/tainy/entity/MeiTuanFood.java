package com.tainy.entity;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2017/12/8
 */
public class MeiTuanFood implements Serializable{

    private Long id;

    private String city;

    private String name;

    private String address;

    private String score;

    private String phone;

    private String businessHours;

    private String consumptionPerPerson;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusinessHours() {
        return businessHours;
    }

    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getConsumptionPerPerson() {
        return consumptionPerPerson;
    }

    public void setConsumptionPerPerson(String consumptionPerPerson) {
        this.consumptionPerPerson = consumptionPerPerson;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
