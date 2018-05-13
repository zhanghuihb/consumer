package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleConsumerInfo;
import com.tainy.common.util.page.Page;
import com.tainy.console.vo.console.dto.QueryByTimeDTO;
import com.tainy.console.vo.console.dto.StatisticsConsumerDataDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsoleConsumerInfoMapper extends BaseMapper<ConsoleConsumerInfo>{

    List<ConsoleConsumerInfo> selectListPageGetConsumerInfos(@Param("page") Page<?> page, @Param("info") ConsoleConsumerInfo consoleConsumerInfo);

    List<StatisticsConsumerDataDTO> statisticsConsumerDataByCategory(@Param("info") ConsoleConsumerInfo info);

    List<StatisticsConsumerDataDTO> statisticsConsumerDataByCity(@Param("info") ConsoleConsumerInfo info);

    List<StatisticsConsumerDataDTO> statisticsConsumerData(@Param("info") ConsoleConsumerInfo info);

    List<StatisticsConsumerDataDTO> statisticsConsumerDataByMonth(@Param("info") ConsoleConsumerInfo info);

    List<StatisticsConsumerDataDTO> statisticsConsumerDataByDay(@Param("queryByTimeDTO")QueryByTimeDTO queryByTimeDTO, @Param("userId")Integer userId);

    List<StatisticsConsumerDataDTO> statisticsConsumerDataByPerson(@Param("queryByTimeDTO")QueryByTimeDTO queryByTimeDTO, @Param("userId")Integer userId);
}