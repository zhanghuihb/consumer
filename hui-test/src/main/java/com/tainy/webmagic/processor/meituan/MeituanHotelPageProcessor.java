package com.tainy.webmagic.processor.meituan;

import com.tainy.entity.MeiTuanHotel;
import com.tainy.webmagic.pipeline.meituan.MeiTuanHotelPipeline;
import com.tainy.webmagic.util.JdbcUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.sql.SQLException;

/**
 * 美团酒店入住商家
 *
 * @author Tainy
 * @date 2017/12/8
 */
public class MeituanHotelPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.sse.com.cn/assortment/stock/list/info/company/index.shtml?COMPANY_CODE=600000");

        MeiTuanHotel meiTuanHotel = new MeiTuanHotel();

        // 部分二：定义如何抽取页面信息，并保存下来
        meiTuanHotel.setCity(page.getHtml().xpath("//div[@class='breadcrumb-nav']/a/text()").toString());
        //page.putField("city", page.getHtml().xpath("//div[@class='relative clear']/span[2]/tidyText()"));

        meiTuanHotel.setName(page.getHtml().xpath("//div[@class='relative clear']/span/tidyText()").toString());
        //page.putField("name", page.getHtml().xpath("//div[@class='relative clear']/span/tidyText()"));

        meiTuanHotel.setAddress(page.getHtml().xpath("//div[@class='fs12 mt6 mb10']/span/tidyText()").toString());
        //page.putField("address", page.getHtml().xpath("//div[@class='fs12 mt6 mb10']/span/tidyText()"));

        meiTuanHotel.setScore(page.getHtml().xpath("//div[@class='other-detail-line1-score']/span/tidyText()").toString());
        //page.putField("score", page.getHtml().xpath("//div[@class='other-detail-line1-score']/span/tidyText()"));

        meiTuanHotel.setPhone(page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[1]/dd/span/tidyText()").toString());
        //page.putField("phone", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[1]/dd/span/tidyText()"));

        meiTuanHotel.setInfomation(page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[2]/dd/span/tidyText()").toString());
        //page.putField("infomation", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[2]/dd/span/tidyText()"));

        meiTuanHotel.setIntroduce(page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[3]/dd/span/tidyText()").toString());
        //page.putField("introduce", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[3]/dd/span/tidyText()"));

        meiTuanHotel.setPolicy(page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[4]/dd/tidyText()").toString());
        //page.putField("policy", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[4]/dd/tidyText()"));

        if (meiTuanHotel.getName() == null){
            //skip this page
            page.setSkip(true);
        }else{
            page.putField("meiTuanHotel", meiTuanHotel);
        }

        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(page.getHtml().links().regex("(http://\\w+.meituan\\.com)").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://hotel\\.meituan\\.com/\\w+/)").all());
        page.addTargetRequests(page.getHtml().links().regex("(http://hotel\\.meituan\\.com/\\w+/pn\\d+/)").all());
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {
        Spider.create(new MeituanHotelPageProcessor())
                //从"https://www.meituan.com/"开始抓
                .addUrl("https://www.meituan.com/")
                //从之前抓取到的URL继续抓取
                .setScheduler(new RedisScheduler("localhost"))
                //保存到数据库
                .addPipeline(new MeiTuanHotelPipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        try {
            if(JdbcUtil.conn != null){
                JdbcUtil.conn.close();   //关闭数据库连接
            }

        } catch (SQLException e) {
        System.out.println("关闭数据库连接时异常" + e.getMessage());
    }
        System.out.println("*******************************************爬取美团酒店结束*******************************************");
    }
}
