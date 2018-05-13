package com.tainy.webmagic.processor.meituan;

import com.tainy.entity.MeiTuanFood;
import com.tainy.webmagic.pipeline.meituan.MeiTuanFoodPipeline;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.selector.Html;

/**
 * @author Tainy
 * @date 2017/12/10
 */
public class MeituanFoodPageProcessor implements PageProcessor {

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    @Override
    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver", "D:\\drivers\\chromedriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.get(page.getUrl().toString());
        driver.get("http://zz.meituan.com/meishi/");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement webElement = driver.findElement(By.xpath("//div[@class='list']"));
        String str = webElement.getAttribute("outerHTML");
        System.out.println(str);

        Html html = new Html(str);
        MeiTuanFood meiTuanFood = new MeiTuanFood();
        // 部分二：定义如何抽取页面信息，并保存下来
        meiTuanFood.setCity(html.xpath("//div[@class='breadcrumbs']/span[1]/a/text()").toString());
        //page.putField("city", page.getHtml().xpath("//div[@class='relative clear']/span[2]/tidyText()"));

        meiTuanFood.setName(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='name']/tidyText()").toString());
        //page.putField("name", page.getHtml().xpath("//div[@class='relative clear']/span/tidyText()"));

        meiTuanFood.setAddress(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='address']/p[1]/tidyText()").toString());
        //page.putField("address", page.getHtml().xpath("//div[@class='fs12 mt6 mb10']/span/tidyText()"));

        meiTuanFood.setScore(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='score clear ']/p/tidyText()").toString());
        //page.putField("score", page.getHtml().xpath("//div[@class='other-detail-line1-score']/span/tidyText()"));

        meiTuanFood.setPhone(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='address']/p[2]/tidyText()").toString());
        //page.putField("phone", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[1]/dd/span/tidyText()"));

        meiTuanFood.setBusinessHours(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='address']/p[3]/tidyText()").toString());
        //page.putField("businessHours", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[2]/dd/span/tidyText()"));

        meiTuanFood.setConsumptionPerPerson(html.xpath("//div[@class='details clear']/div[@class='d-left']/div[@class='score clear']/p/span/tidyText()").toString());
        //page.putField("consumptionPerPerson", page.getHtml().xpath("//div[@class='poi-hotelinfo-content clearfix']/div[3]/dd/span/tidyText()"));

        if (meiTuanFood.getName() == null){
            //skip this page
            page.setSkip(true);
        }else{
            page.putField("meiTuanFood", meiTuanFood);
        }


        // 部分三：从页面发现后续的url地址来抓取
        page.addTargetRequests(html.links().regex("(http://zz\\.meituan\\.com)").all());
        page.addTargetRequests(html.links().regex("(http://zz\\.meituan\\.com/meishi/\\d+/)").all());

        driver.close();
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MeituanFoodPageProcessor())
                //从"https://www.meituan.com/"开始抓
                .addUrl("http://zz.meituan.com/meishi/")
                //从之前抓取到的URL继续抓取
                .setScheduler(new RedisScheduler("localhost"))
                //保存到数据库
                .addPipeline(new MeiTuanFoodPipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();

        System.out.println("*******************************************爬取美团美食结束*******************************************");
    }
}
