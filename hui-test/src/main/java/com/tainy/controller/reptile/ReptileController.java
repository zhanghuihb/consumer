package com.tainy.controller.reptile;

import com.alibaba.fastjson.JSON;
import com.wordnik.swagger.annotations.ApiOperation;
import com.tainy.common.BaseController;
import com.tainy.common.BaseResponse;
import com.tainy.webmagic.pipeline.meituan.MaoYanFilmPipeline;
import com.tainy.webmagic.pipeline.meituan.MeiTuanFoodPipeline;
import com.tainy.webmagic.processor.meituan.MaoYanFilmPageProcessor;
import com.tainy.webmagic.processor.meituan.MeituanFoodPageProcessor;
import com.tainy.webmagic.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.sql.SQLException;

/**
 * 爬虫请求控制器
 *
 * @author Tainy
 * @date 2017/10/8
 */
@Controller
@RequestMapping("/v1/reptile")
public class ReptileController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(ReptileController.class);

    @RequestMapping(value = "/maoyanfilm", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "爬取猫眼电影接口", httpMethod = "POST", response = BaseResponse.class, notes = "猫眼电影")
    public ResponseEntity<String> maoyanfilm(){

        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("219.138.58.187",3128),
                new Proxy("219.138.58.222",3128),new Proxy("112.114.92.40",8118),
                new Proxy("110.73.5.192",8132),new Proxy("219.138.58.106",3128),
                new Proxy("219.138.58.132",3128),new Proxy("219.138.58.130",3128)));
        LOGGER.info("代理IP池" + JSON.toJSONString(httpClientDownloader));
        Spider.create(new MaoYanFilmPageProcessor())
                //从"https://www.meituan.com/"开始抓
                .addUrl("http://maoyan.com/films")
                //从之前抓取到的URL继续抓取
                .setScheduler(new RedisScheduler("localhost"))
                //保存到数据库
                .addPipeline(new MaoYanFilmPipeline())
                //代理
                .setDownloader(httpClientDownloader)
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
        System.out.println("********************************************爬取猫眼电影结束******************************************");

        return null;
    }

    @RequestMapping(value = "/meituanFood", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "爬取美团美食接口", httpMethod = "POST", response = BaseResponse.class, notes = "美团美食")
    public ResponseEntity<String> meituanFood(){

        /*HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(
                new Proxy("110.73.5.192",8132),new Proxy("219.138.58.106",3128),
                new Proxy("219.138.58.132",3128),new Proxy("219.138.58.130",3128)));*/

        Spider.create(new MeituanFoodPageProcessor())
                //从"https://www.meituan.com/"开始抓
                .addUrl("http://zz.meituan.com/meishi/")
                //从之前抓取到的URL继续抓取
                .setScheduler(new RedisScheduler("localhost"))
                //保存到数据库
                .addPipeline(new MeiTuanFoodPipeline())
                //代理
                //.setDownloader(httpClientDownloader)
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
        System.out.println("********************************************爬取美团美食结束******************************************");

        return null;
    }
}
