package com.tainy.webmagic.processor.爬取大众点评美食商家;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Tainy
 * @date 2018/5/31 19:51
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DianPingFoodShop implements Serializable{

    private Integer id;

    private String shopName;

    private String address;

    private String stars;// 星级

    private String reviewCount;// 评论数

    private String avgPriceTitle;// 人均消费

    private String favors;// 口味

    private String envirment;// 环境

    private String service;// 服务

    private String phone;// 联系方式

    private String openTime;// 营业时间

}
