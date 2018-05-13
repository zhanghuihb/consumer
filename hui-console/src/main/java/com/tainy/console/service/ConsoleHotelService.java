package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleHotel;
import com.tainy.common.util.page.Page;
import com.tainy.console.vo.console.vo.StatisticsAccordingAreaVO;
import com.tainy.console.vo.console.vo.StatisticsAccordingScoreVO;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/11
 */
public interface ConsoleHotelService {

    List<ConsoleHotel> getAllHotels(ConsoleHotel hotel);

    List<ConsoleHotel> selectListPageGetHotels(Page<?> page, ConsoleHotel hotel);

    List<StatisticsAccordingAreaVO> statisticsAccordingArea();

    List<StatisticsAccordingScoreVO> statisticsAccordingScore();
}
