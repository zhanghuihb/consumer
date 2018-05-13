package com.tainy.console.mapper;

import com.tainy.common.base.BaseMapper;
import com.tainy.common.domain.console.ConsoleConsumerCategory;

import java.util.List;

public interface ConsoleConsumerCategoryMapper extends BaseMapper<ConsoleConsumerCategory>{

    List<ConsoleConsumerCategory> getAllCodes();
}