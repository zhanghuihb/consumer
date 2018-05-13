package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleHotel;
import com.tainy.common.util.page.Page;
import com.tainy.console.mapper.ConsoleHotelMapper;
import com.tainy.console.service.ConsoleHotelService;
import com.tainy.console.vo.console.vo.StatisticsAccordingAreaVO;
import com.tainy.console.vo.console.vo.StatisticsAccordingScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/11
 */
@Service
public class ConsoleHotelServiceImpl implements ConsoleHotelService{

    @Autowired
    private ConsoleHotelMapper consoleHotelMapper;

    @Override
    public List<ConsoleHotel> getAllHotels(ConsoleHotel hotel) {
        return consoleHotelMapper.queryByObject(hotel);
    }

    @Override
    public List<ConsoleHotel> selectListPageGetHotels(Page<?> page, ConsoleHotel hotel) {
        return consoleHotelMapper.selectListPageGetHotels(page, hotel);
    }

    @Override
    public List<StatisticsAccordingAreaVO> statisticsAccordingArea() {
        return consoleHotelMapper.statisticsAccordingArea();
    }

    @Override
    public List<StatisticsAccordingScoreVO> statisticsAccordingScore() {
        return consoleHotelMapper.statisticsAccordingScore();
    }
}
