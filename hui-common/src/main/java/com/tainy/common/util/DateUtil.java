package com.tainy.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tainy
 * @date 2018/4/3 15:42
 */
public class DateUtil {

    public static final String YMD = "yyyy-MM-dd";

    public static final String HMS = "HH:mm:ss";

    public static final String HM = "HH:mm";

    public static final String YMDHM = "yyyy-MM-dd HH:mm";

    public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final String YMDHMSS = "yyyyMMddHHmmssSSS";

    public static final String YMDEHMS = "yyyy-MM-dd E HH:mm:ss";

    public static String formatYMD(Date date){
        return new SimpleDateFormat(YMD).format(date);
    }

    public static String formatHMS(Date date){
        return new SimpleDateFormat(HMS).format(date);
    }

    public static String formatYMDHM(Date date){
        return new SimpleDateFormat(YMDHM).format(date);
    }

    public static String formatYMDHMS(Date date){
        return new SimpleDateFormat(YMDHMS).format(date);
    }

    public static String formatYMDHMSS(Date date){
        return new SimpleDateFormat(YMDHMSS).format(date);
    }

    public static Date parseYMDHMS(String date) throws ParseException {
        return new SimpleDateFormat(YMDHMS).parse(date);
    }

    public static Date parseYMD(String date) throws ParseException {
        return new SimpleDateFormat(YMD).parse(date);
    }

    /**
     * 格式化时间
     * @param queryTime
     * @return
     */
    public static String formatQueryTime(String queryTime){
        queryTime = queryTime + ":00";
        queryTime = queryTime.replace("T", " ");
        return queryTime;
    }

    /**
     * 格式化时间
     * @param queryTime
     * @return
     */
    public static Date formatQueryTimeToData(String queryTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(queryTime);
    }


    /**
     * 计算两个日期之间的天数差(含首尾两天)
     *
     * @param begin
     * @param end
     * @return
     */
    public static long betweenDays(Date begin, Date end) {
        long time1 = 0;
        long time2 = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(sdf.format(begin)));
            time1 = calendar.getTimeInMillis();

            calendar.setTime(sdf.parse(sdf.format(end)));
            time2 = calendar.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (time2 - time1) / (24 * 3600 * 1000) + 1;
    }


    /**
     * 计算两个日期之间的天数差(含首尾两天)
     *
     * @param begin
     * @param end
     * @return
     */
    public static long betweenDays(String begin, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return betweenDays(sdf.parse(begin), sdf.parse(end));
    }

    /**
     * 给定一个时间，往前推算days天，返回一个新的日期
     *
     * @author Tainy
     * @date   2018/4/3 15:55
     * @param days 正数 代表往后推算; 负数，代表往前推算
     */
    public static Date genOneNewTime(Date currentTime, Integer days){
        SimpleDateFormat sdf = new SimpleDateFormat(YMDHMS);
        String result = null;
        try {
            sdf.format(currentTime);//当前的时间
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, days);//
            result=sdf.format(cal.getTime());
            return sdf.parse(result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        // 计算时间间隔天数
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = new Date();
            Date endTime =sdf.parse("2019-02-05 00:00:02");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(sdf.format(startTime)));
            Long Time1 = calendar.getTimeInMillis();

            calendar.setTime(sdf.parse(sdf.format(endTime)));
            Long time2 = calendar.getTimeInMillis();

            System.out.println((Time1 - time2) / (24 * 3600 * 1000) + 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       /* // 计算n天后的日期
        genOneNewTime(new Date(), 30);*/
    }
}
