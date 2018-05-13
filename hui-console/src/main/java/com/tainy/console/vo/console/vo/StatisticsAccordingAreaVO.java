package com.tainy.console.vo.console.vo;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/1/12
 */
public class StatisticsAccordingAreaVO implements Serializable{

    private String name;

    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
