package com.tainy.console.vo.console;

import com.tainy.console.vo.console.vo.StatisticsAccordingAreaVO;
import com.tainy.console.vo.console.vo.StatisticsAccordingScoreVO;

import java.io.Serializable;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/12
 */
public class StatisticsResponse implements Serializable{
    //区域
    private List<StatisticsAccordingAreaVO> areaList;
    //评分
    private List<StatisticsAccordingScoreVO> scoreList;

    public List<StatisticsAccordingAreaVO> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<StatisticsAccordingAreaVO> areaList) {
        this.areaList = areaList;
    }

    public List<StatisticsAccordingScoreVO> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<StatisticsAccordingScoreVO> scoreList) {
        this.scoreList = scoreList;
    }
}
