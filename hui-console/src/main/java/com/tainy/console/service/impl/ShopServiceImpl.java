package com.tainy.console.service.impl;

import com.tainy.common.domain.console.ConsoleShop;
import com.tainy.common.page.PageInfo;
import com.tainy.common.vo.console.QueryShopsRequest;
import com.tainy.console.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public ConsoleShop queryShopsById(Integer id) {
        ConsoleShop shop = solrTemplate.getById(id, ConsoleShop.class);
        return shop;
    }

    @Override
    public PageInfo queryShops(QueryShopsRequest request) {
        PageInfo pageInfo = PageInfo.newInstance(request.getCurrentPage(), request.getPageSize());
        if(request == null){
            return pageInfo;
        }
        SimpleQuery query = new SimpleQuery();
        query.addCriteria(new Criteria("shopName").is(request.getShopName()));
        ScoredPage<ConsoleShop> scoredPage = solrTemplate.queryForPage(query, ConsoleShop.class);
        if(scoredPage != null){
            pageInfo.setTotalPage(scoredPage.getTotalPages());
            pageInfo.setTotalRecord(scoredPage.getTotalElements());
            List<ConsoleShop> shops = solrTemplate.queryForPage(query, ConsoleShop.class).getContent();
            if(!CollectionUtils.isEmpty(shops)){
                pageInfo.setData(shops);
            }
        }

        return pageInfo;
    }
}
