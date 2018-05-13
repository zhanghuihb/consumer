package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleConsumerInfo;
import com.tainy.common.util.page.Page;
import com.tainy.console.vo.console.StatisticsConsumerDataRequest;
import com.tainy.console.vo.console.StatisticsConsumerDataResponse;

import java.text.ParseException;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/13
 */
public interface ConsoleConsumerInfoService {

    List<ConsoleConsumerInfo> selectListPageGetConsumerInfos(Page<?> page, ConsoleConsumerInfo consoleConsumerInfo);

    boolean addConsumerInfo(ConsoleConsumerInfo info);

    boolean editConsumerInfo(ConsoleConsumerInfo info);

    Integer deleteConsumerInfo(Integer id);

    StatisticsConsumerDataResponse statisticsConsumerData(StatisticsConsumerDataRequest statisticsConsumerDataRequest) throws ParseException;
}
