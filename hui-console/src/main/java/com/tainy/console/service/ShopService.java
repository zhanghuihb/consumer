package com.tainy.console.service;

import com.tainy.common.domain.console.ConsoleShop;
import com.tainy.common.page.PageInfo;
import com.tainy.common.vo.console.QueryShopsRequest;

/**
 * @author Tainy
 * @date 2018/8/13 16:39
 */
public interface ShopService{

    ConsoleShop queryShopsById(Integer id);

    PageInfo getShops(QueryShopsRequest request);
}
