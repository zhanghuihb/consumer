package com.tainy.webmagic.processor.meituan;

import com.tainy.entity.MaoYanFilm;
import com.tainy.util.StringUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author Tainy
 * @date 2017/12/12
 */
public class MaoYanFilmPageProcessor implements PageProcessor{

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        MaoYanFilm maoYanFilm = new MaoYanFilm();

        // 部分二：定义如何抽取页面信息，并保存下来
        String name = page.getHtml().xpath("//div[@class='movie-brief-container']/div/tidyText()").toString();
        maoYanFilm.setName(name);

        String alias = page.getHtml().xpath("//div[@class='movie-brief-container']/h3/tidyText()").toString();
        maoYanFilm.setAlias(alias);

        String director = page.getHtml().xpath("//li[@class='celebrity'][1]/div/a/text()").toString();
        maoYanFilm.setDirector(director);

        String protagonist = page.getHtml().xpath("//li[@class='celebrity actor'][1]/div/a/text()").toString()
                + (page.getHtml().xpath("//li[@class='celebrity actor'][2]/div/a/text()") == null ? "" : "," + page.getHtml().xpath("//li[@class='celebrity actor'][2]/div/a/text()").toString())
                + (page.getHtml().xpath("//li[@class='celebrity actor'][3]/div/a/text()") == null ? "" : "," + page.getHtml().xpath("//li[@class='celebrity actor'][3]/div/a/text()").toString());
        maoYanFilm.setProtagonist(protagonist.trim().replace("null",""));

        String type = page.getHtml().xpath("//div[@class='movie-brief-container']/ul/li[1]/tidyText()").toString();
        maoYanFilm.setType(StringUtil.isEmpty(type) ? "" : type.replace("*", "").trim());

        String area = page.getHtml().xpath("//div[@class='movie-brief-container']/ul/li[2]/tidyText()").toString();
        maoYanFilm.setArea(StringUtil.isEmpty(area) ? "" : area.replace("*", "").trim());

        String releaseDate = page.getHtml().xpath("//div[@class='movie-brief-container']/ul/li[3]/tidyText()").toString();
        maoYanFilm.setReleaseDate(StringUtil.isEmpty(releaseDate) ? "" : releaseDate.replace("*", "").trim());

        String commentNumber = page.getHtml().xpath("//span[@class='score-num']/tidyText()").toString();
        maoYanFilm.setCommentNumber(StringUtil.isEmpty(commentNumber) ? "" : commentNumber.trim().replace("null",""));

        String commentScore = page.getHtml().xpath("//span[@class='index-left info-num']/span/tidyText()").toString();
        maoYanFilm.setCommenScore(StringUtil.isEmpty(commentScore) ? "" : commentScore.trim());

        String boxOffice = page.getHtml().xpath("//div[@class='movie-index-content box']/span[1]/tidyText()").toString();
        maoYanFilm.setBoxOffice(StringUtil.isEmpty(boxOffice) ? "" : boxOffice.trim());

        String boxOfficeUnit = page.getHtml().xpath("//div[@class='movie-index-content box']/span[2]/tidyText()").toString();
        maoYanFilm.setBoxOfficeUnit(StringUtil.isEmpty(boxOfficeUnit) ? "" : boxOfficeUnit.trim());

        String introduce = page.getHtml().xpath("//span[@class='dra']/tidyText()").toString();
        maoYanFilm.setIntroduce(StringUtil.isEmpty(introduce) ? "" : introduce.trim());

        if (maoYanFilm.getName() == null && maoYanFilm.getAlias() == null){
            //skip this page
            page.setSkip(true);
        }else{
            page.putField("maoYanFilm", maoYanFilm);
        }

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(.*/films/\\w+/)").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://maoyan.com/films/?.*)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
