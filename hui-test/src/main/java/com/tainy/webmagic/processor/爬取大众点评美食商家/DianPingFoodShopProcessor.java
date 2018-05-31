package com.tainy.webmagic.processor.爬取大众点评美食商家;

import com.tainy.webmagic.processor.爬取歌曲.ZanmeishiPageProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 爬取大众点评美食商家
 *
 * @author Tainy
 * @date 2018/5/31 20:04
 */
public class DianPingFoodShopProcessor implements PageProcessor {

    private static Logger LOGGER = LoggerFactory.getLogger(ZanmeishiPageProcessor.class);

    /** 一、抓取网站的相关配置，包括编码、抓取间隔和重试次数等 */
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    /** 二、process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑 */
    @Override
    public void process(Page page) {

    }

    @Override
    public Site getSite() {
        return this.site;
    }
}
