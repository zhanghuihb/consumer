package com.tainy.entity;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2017/12/12
 */
public class MaoYanFilm implements Serializable{

    private Long id;

    private String name;

    private String alias;

    private String director;

    private String protagonist;

    private String type;

    private String area;

    private String releaseDate;

    private String commentNumber;

    private String commenScore;

    private String boxOffice;

    private String boxOfficeUnit;

    private String introduce;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProtagonist() {
        return protagonist;
    }

    public void setProtagonist(String protagonist) {
        this.protagonist = protagonist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCommenScore() {
        return commenScore;
    }

    public void setCommenScore(String commenScore) {
        this.commenScore = commenScore;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getBoxOfficeUnit() {
        return boxOfficeUnit;
    }

    public void setBoxOfficeUnit(String boxOfficeUnit) {
        this.boxOfficeUnit = boxOfficeUnit;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
