package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleShop;
import com.tainy.console.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/8/13 16:39
 */
@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public ConsoleShop queryShops() {
        ConsoleShop shop = solrTemplate.getById(24, ConsoleShop.class);
        return shop;
    }
}
