package com.tainy.webmagic.pipeline.meituan;

import com.alibaba.fastjson.JSON;
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
 * @date 2017/12/8
 */
public class MeiTuanHotelPipeline implements Pipeline {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void process(ResultItems resultItems, Task task) {
        LOGGER.info("爬取美团网Hotel信息 持久化 参数:" + JSON.toJSONString(resultItems));

        MeiTuanHotel meiTuanHotel = (MeiTuanHotel) resultItems.get("meiTuanHotel");

        meiTuanHotel.setCreateTime(sdf.format(new Date()));
        //持久化sql
        String sql = "INSERT INTO HUI_MEITUAN_HOTEL(city, name, address, score, phone, information, introduce, policy, create_time)"
                + " VALUES ('" + meiTuanHotel.getCity() + "','" + meiTuanHotel.getName() + "','" + meiTuanHotel.getAddress() + "','" + meiTuanHotel.getScore() + "','"
                + meiTuanHotel.getPhone() + "','" + meiTuanHotel.getInfomation() + "','" + meiTuanHotel.getIntroduce() + "','" + meiTuanHotel.getPolicy() + "','" + meiTuanHotel.getCreateTime() + "')";
        LOGGER.info("爬取美团网Hotel信息 持久化 sql:" + JSON.toJSONString(sql));

        try {
            JdbcUtil.insert(sql, "HUI_MEITUAN_HOTEL");
        } catch (Exception e) {
            LOGGER.info("插入数据时异常", e);
        }
    }
}
