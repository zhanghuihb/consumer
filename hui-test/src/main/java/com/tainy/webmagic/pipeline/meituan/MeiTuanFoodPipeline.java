package com.tainy.webmagic.pipeline.meituan;

import com.alibaba.fastjson.JSON;
import com.tainy.entity.MeiTuanFood;
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
public class MeiTuanFoodPipeline implements Pipeline {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void process(ResultItems resultItems, Task task) {
        LOGGER.info("爬取美团网Food信息 持久化 参数:" + JSON.toJSONString(resultItems));

        MeiTuanFood meiTuanFood = (MeiTuanFood) resultItems.get("meiTuanFood");

        meiTuanFood.setCreateTime(sdf.format(new Date()));
        //持久化sql
        String sql = "INSERT INTO HUI_MEITUAN_FOOD(city, name, address, score, phone, businessHours, consumptionPerPerson, create_time)"
                + " VALUES ('" + meiTuanFood.getCity() + "','" + meiTuanFood.getName() + "','" + meiTuanFood.getAddress() + "','" + meiTuanFood.getScore() + "','"
                + meiTuanFood.getPhone() + "','" + meiTuanFood.getBusinessHours() + "','" + meiTuanFood.getConsumptionPerPerson() + "','" + meiTuanFood.getCreateTime() + "')";
        LOGGER.info("爬取美团网Food信息 持久化 sql:" + JSON.toJSONString(sql));

        try {
            JdbcUtil.insert(sql, "HUI_MEITUAN_FOOD");
        } catch (Exception e) {
            LOGGER.info("插入数据时异常", e);
        }
    }
}
