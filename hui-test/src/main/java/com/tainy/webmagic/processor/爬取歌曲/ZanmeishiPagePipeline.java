package com.tainy.webmagic.processor.爬取歌曲;

import com.alibaba.fastjson.JSON;
import com.tainy.entity.MeiTuanHotel;
import com.tainy.util.httpclient.DownloadFile;
import com.tainy.webmagic.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Date;
import java.util.List;

/**
 * @author Tainy
 * @date 2018/5/12
 */
public class ZanmeishiPagePipeline implements Pipeline{

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Music> musicList = (List<Music>) resultItems.get("musicList");

        if(!CollectionUtils.isEmpty(musicList)){
            LOGGER.info("爬取赞美诗歌曲 持久化 begin...");
            try {
                JdbcUtil.batchInsert(musicList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER.info("爬取赞美诗歌曲 持久化 finish...");
        }else{
            LOGGER.info("music为空，跳过...");
        }
    }
}
