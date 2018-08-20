package com.tainy.console.service.impl;

import com.alibaba.fastjson.JSON;
import com.tainy.common.domain.console.ConsoleShop;
import com.tainy.common.page.PageInfo;
import com.tainy.common.vo.console.QueryShopsRequest;
import com.tainy.console.service.ShopService;
import org.apache.solr.client.solrj.SolrQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/8/13 16:39
 */
@Service
public class ShopServiceImpl implements ShopService{

    protected static Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public ConsoleShop queryShopsById(Integer id) {
        ConsoleShop shop = solrTemplate.getById(id, ConsoleShop.class);
        return shop;
    }

    @Override
    public PageInfo getShops(QueryShopsRequest request) {
        PageInfo pageInfo = PageInfo.newInstance(request.getCurrentPage(), request.getPageSize());
        if(request == null){
            return pageInfo;
        }
        HighlightQuery query = new SimpleHighlightQuery(new SimpleStringCriteria("*:*"));
        HighlightOptions options = new HighlightOptions();// 配置高亮选项
        options.addField("shopName");// 高亮域
        options.addField("address");// 高亮域
        options.addField("province");// 高亮域
        options.addField("classify");// 高亮域
        options.setSimplePrefix("<span style=\"color:red;\">");// 前缀
        options.setSimplePostfix("</span>");// 后缀

        query.setHighlightOptions(options);

        query.setPageRequest(new PageRequest(pageInfo.getCurrentPage(),pageInfo.getPageSize()));
        query.addCriteria(new Criteria("status").is(ConsoleShop.STATUS.PUBLISHED.getType()));
        if(!StringUtils.isEmpty(request.getShopName())){
            query.addCriteria(new Criteria("keywords").is(request.getShopName()));
        }
        HighlightPage<ConsoleShop> highlightPage = solrTemplate.queryForHighlightPage(query, ConsoleShop.class);
        if(highlightPage != null){
            List<HighlightEntry<ConsoleShop>> consoleHighlightEntrys = highlightPage.getHighlighted();
            if(!CollectionUtils.isEmpty(consoleHighlightEntrys)){
                for(HighlightEntry<ConsoleShop> consoleShopHighlightEntry : consoleHighlightEntrys){
                    ConsoleShop shop = consoleShopHighlightEntry.getEntity();
                    // 获取高亮数据
                    List<HighlightEntry.Highlight> highlights = consoleShopHighlightEntry.getHighlights();
                    if(!CollectionUtils.isEmpty(highlights)){
                        LOGGER.info("高亮显示 {}", JSON.toJSONString(highlights));
                        for(int i = 0; i < highlights.size(); i++){
                            // 分类
                            if(!CollectionUtils.isEmpty(highlights.get(i).getSnipplets())){
                                // 名称
                                if(highlights.get(i).getField().getName().equals("shopName")){
                                    shop.setShopName(highlights.get(i).getSnipplets().get(0));
                                } else if(highlights.get(i).getField().getName().equals("address")){
                                    shop.setAddress(highlights.get(i).getSnipplets().get(0));
                                } else if(highlights.get(i).getField().getName().equals("province")){
                                    shop.setProvince(highlights.get(i).getSnipplets().get(0));
                                } else if(highlights.get(i).getField().getName().equals("classify")){
                                    shop.setClassify(highlights.get(i).getSnipplets().get(0));
                                }
                            }
                        }
                    }
                }
            }
            if(!CollectionUtils.isEmpty(highlightPage.getContent())){
                pageInfo.setTotalPage(highlightPage.getTotalPages());
                pageInfo.setTotalRecord(highlightPage.getTotalElements());
                pageInfo.setData(highlightPage.getContent());
            }else{
                pageInfo.setData(new ArrayList<>());
            }
        }

        return pageInfo;
    }
}
