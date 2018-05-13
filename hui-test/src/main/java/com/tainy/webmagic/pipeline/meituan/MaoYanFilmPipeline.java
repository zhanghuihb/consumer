package com.tainy.webmagic.pipeline.meituan;

import com.alibaba.fastjson.JSON;
import com.tainy.entity.MaoYanFilm;
import com.tainy.entity.MeiTuanHotel;
import com.tainy.webmagic.util.JdbcUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Tainy
 * @date 2017/12/12
 */
public class MaoYanFilmPipeline implements Pipeline{

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void process(ResultItems resultItems, Task task) {

        LOGGER.info("爬取猫眼Film信息 持久化 参数:" + JSON.toJSONString(resultItems));

        MaoYanFilm maoYanFilm = (MaoYanFilm) resultItems.get("maoYanFilm");

        maoYanFilm.setCreateTime(sdf.format(new Date()));
        //持久化sql
        String sql = "INSERT INTO HUI_MAOYAN_FILM(name, alias, director, protagonist, type, area, release_date, comment_number, comment_score, box_office, box_office_unit, introduce, create_time)"
                + " VALUES ('" + maoYanFilm.getName() + "','" + maoYanFilm.getAlias() + "','" + maoYanFilm.getDirector() + "','" + maoYanFilm.getProtagonist() + "','" + maoYanFilm.getType() + "','"
                + maoYanFilm.getArea() + "','" + maoYanFilm.getReleaseDate() + "','" + maoYanFilm.getCommentNumber() + "','" + maoYanFilm.getCommenScore() + "','" + maoYanFilm.getBoxOffice() + "','"
                + maoYanFilm.getBoxOfficeUnit() + "','" + maoYanFilm.getIntroduce() + "','" + maoYanFilm.getCreateTime() + "')";
        LOGGER.info("爬取猫眼Film信息 持久化 sql:" + JSON.toJSONString(sql));

        try {
            JdbcUtil.insert(sql, "HUI_MAOYAN_FILM");
        } catch (Exception e) {
            LOGGER.info("插入数据时异常", e);
        }
    }
}
