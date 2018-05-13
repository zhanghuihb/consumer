package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleHotel;
import com.tainy.common.util.page.Page;
import com.tainy.console.vo.console.vo.StatisticsAccordingAreaVO;
import com.tainy.console.vo.console.vo.StatisticsAccordingScoreVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsoleHotelMapper extends BaseMapper<ConsoleHotel>{

    List<ConsoleHotel> selectListPageGetHotels(@Param("page") Page<?> page, @Param("hotel") ConsoleHotel hotel);

    List<StatisticsAccordingAreaVO> statisticsAccordingArea();

    List<StatisticsAccordingScoreVO> statisticsAccordingScore();
}