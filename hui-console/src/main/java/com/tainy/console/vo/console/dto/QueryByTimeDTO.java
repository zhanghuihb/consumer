package com.tainy.console.vo.console.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Tainy
 * @date 2018/4/3 16:21
 */
public class QueryByTimeDTO implements Serializable{

    private Date startTime;

    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
