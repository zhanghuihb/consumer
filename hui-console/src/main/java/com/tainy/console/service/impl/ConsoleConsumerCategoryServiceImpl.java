package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleConsumerCategory;
import com.tainy.console.mapper.ConsoleConsumerCategoryMapper;
import com.tainy.console.service.ConsoleConsumerCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tainy
 * @date 2018/1/13
 */
@Service
public class ConsoleConsumerCategoryServiceImpl implements ConsoleConsumerCategoryService{

    @Autowired
    private ConsoleConsumerCategoryMapper consoleConsumerCategoryMapper;

    @Override
    public List<ConsoleConsumerCategory> getAllCodes() {
        return consoleConsumerCategoryMapper.getAllCodes();
    }
}
