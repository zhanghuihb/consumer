package com.tainy.common.domain.console;

import java.io.Serializable;

public class ShuangSeQiu implements Serializable{

    private Integer id;

    private String date;

    private String red1;

    private String red2;

    private String red3;

    private String red4;

    private String red5;

    private String red6;

    private String blue1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public String getRed1() {
        return red1;
    }

    public void setRed1(String red1) {
        this.red1 = red1 == null ? null : red1.trim();
    }

    public String getRed2() {
        return red2;
    }

    public void setRed2(String red2) {
        this.red2 = red2 == null ? null : red2.trim();
    }

    public String getRed3() {
        return red3;
    }

    public void setRed3(String red3) {
        this.red3 = red3 == null ? null : red3.trim();
    }

    public String getRed4() {
        return red4;
    }

    public void setRed4(String red4) {
        this.red4 = red4 == null ? null : red4.trim();
    }

    public String getRed5() {
        return red5;
    }

    public void setRed5(String red5) {
        this.red5 = red5 == null ? null : red5.trim();
    }

    public String getRed6() {
        return red6;
    }

    public void setRed6(String red6) {
        this.red6 = red6 == null ? null : red6.trim();
    }

    public String getBlue1() {
        return blue1;
    }

    public void setBlue1(String blue1) {
        this.blue1 = blue1 == null ? null : blue1.trim();
    }
}