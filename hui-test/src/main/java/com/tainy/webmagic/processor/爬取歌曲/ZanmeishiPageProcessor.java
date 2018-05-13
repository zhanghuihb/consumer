package com.tainy.webmagic.processor.爬取歌曲;

import com.alibaba.fastjson.JSON;
import com.tainy.util.httpclient.HttpClient;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取赞美诗网站圣经歌曲
 *
 *
 * @author Tainy
 * @date 2018/5/11
 */
public class ZanmeishiPageProcessor implements PageProcessor{

    private static Logger LOGGER = LoggerFactory.getLogger(ZanmeishiPageProcessor.class);

    /** 一、抓取网站的相关配置，包括编码、抓取间隔和重试次数等 */
    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36");

    /** 二、process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑 */
    @Override
    public void process(Page page) {
        // 部分三：从页面发现后续的url地址来抓取
        List<String> urlList1 = page.getHtml().links().regex("(/album/zan-mei-shi-ge-\\w+-\\w+.html)").all();
        if(!CollectionUtils.isEmpty(urlList1)){
            // urlList1.stream().forEach(e -> {e = Constants.ZANMEISHI_URL + e;});
            List<String> list = new ArrayList<>();
            for(String str : urlList1){
                str = Constants.ZANMEISHI_URL + str;
                list.add(str);
            }
            LOGGER.info("list {}",JSON.toJSONString(list));
            page.addTargetRequests(list);
        }

        if(page.getUrl() != null && page.getUrl().toString().contains(Constants.ZAN_MEI_SHI_GE)){
            List<Music> musicList = new ArrayList<>();
            Music music = null;
            for(int i = 0 ; i < 200; i++){
                List<String> urlNameList = page.getHtml().xpath("//div[@class='songs mt5']/table/tbody/tr[" + (i + 1) + "]/td[@class='btn']/a[@class='btn_down']").all();
                try {
                    music = dealWithData(urlNameList);
                    if(music != null){
                        musicList.add(music);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            page.putField("musicList",musicList);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ZanmeishiPageProcessor())
                //从"http://www.zanmeishi.com/artist/hymns-book.html"开始抓
                .addUrl("http://www.zanmeishi.com/artist/hymns-book.html")
                //从之前抓取到的URL继续抓取
                .setScheduler(new RedisScheduler("localhost"))
                //保存到数据库
                .addPipeline(new ZanmeishiPagePipeline())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
        System.out.println("*******************************************爬取赞美诗歌曲结束*******************************************");
    }

    private Music dealWithData(List<String> urlNameList) throws Exception {
        if(!CollectionUtils.isEmpty(urlNameList)) {
            String[] temp = urlNameList.get(0).split("'");
            String urlName =Constants.DOWNLOAD_MUSIC_URL_PREFIX + temp[1];
            String result = HttpClient.sendGet(urlName,Constants.COOKIE,null);
            // 转JSON
            if(StringUtils.isNotEmpty(result)){
                JSONObject json = JSONObject.fromObject(result);
                Music music = new Music();
                music.setSong(json.get("song") == null ? null : json.get("song").toString());
                music.setAlbum(json.get("album") == null ? null : json.get("album").toString());
                music.setArtist(json.get("artist") == null ? null : json.get("artist").toString());
                music.setMsg(json.get("msg") == null ? null : json.get("msg").toString());
                music.setGetUrl(urlName);
                String highDownUrl = json.getJSONObject("down") == null ? null : json.getJSONObject("down").get("url") == null ? null : json.getJSONObject("down").get("url").toString();
                music.setHighDownUrl(highDownUrl);
                String lowDownUrl = json.getJSONObject("play") == null ? null : json.getJSONObject("play").get("url") == null ? null : json.getJSONObject("play").get("url").toString();
                music.setLowDownUrl(lowDownUrl);
                return music;
            }
        }
        return null;
    }
}
