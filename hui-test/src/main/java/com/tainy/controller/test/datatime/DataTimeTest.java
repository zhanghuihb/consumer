package com.tainy.controller.test.datatime;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;

/**
 * @author Tainy
 * @date 2018/5/24 16:32
 */
public class DataTimeTest {

    private static Calendar cal = Calendar.getInstance();

    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static DateTimeFormatter dtfhms = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前月的第一天
     *
     * @author Tainy
     * @date   2018/5/24 17:04
     */
    public static String getFirstDayOfMonth(){
        LocalDate today = LocalDate.now();
        // LocalDate firstday = LocalDate.of(today.getYear(),today.getMonth(),1);
        LocalDate firstday = today.with(TemporalAdjusters.firstDayOfMonth());
        return firstday.format(dtf);
    }

    /**
     * 获取当前月的最后一天
     *
     * @author Tainy
     * @date   2018/5/24 17:04
     */
    public static String getLastDayOfMonth(){
        LocalDate today = LocalDate.now();
        LocalDate lastday = today.with(TemporalAdjusters.lastDayOfMonth());
        return lastday.format(dtf);
    }


    /**
     * 当前时刻往前推一天
     *
     * @author Tainy
     * @date   2018/5/24 17:04
     */
    public static String getLastDayTimeOfNow(){
        /*cal.add(Calendar.DATE, -1);
        return sdf.format(cal.getTime());*/
        LocalDateTime today = LocalDateTime.now();
        System.out.println("today is " + today);
        LocalDateTime yesterday = today.minusDays(1);
        return dtfhms.format(yesterday);
    }


    public static void main(String[] args) {
        /*Calendar cal = Calendar.getInstance();
        System.out.println("年 "+ cal.get(Calendar.YEAR));// 1
        System.out.println("月 "+ cal.get(Calendar.MONTH));// 2
        System.out.println("日 "+ cal.get(Calendar.DAY_OF_MONTH));// 5
        System.out.println("时 "+ cal.get(Calendar.HOUR));// 10
        System.out.println("分 "+ cal.get(Calendar.MINUTE));// 12
        System.out.println("秒 "+ cal.get(Calendar.SECOND));// 13
        System.out.println("一年中的第几周 "+ cal.get(Calendar.WEEK_OF_YEAR));// 3

        LocalDateTime dt = LocalDateTime.now();
        System.out.println("年 "+ dt.getYear());// 1
        System.out.println("月 "+ dt.getMonth());// 2
        System.out.println("日 "+ dt.getDayOfMonth());// 5
        System.out.println("时 "+ dt.getHour());// 10
        System.out.println("分 "+ dt.getMinute());// 12
        System.out.println("秒 "+ dt.getSecond());// 13
        System.out.println("一年中的第几天 "+ dt.getDayOfYear());// 3
        System.out.println("一月中的第几天 "+ dt.getDayOfMonth());// 3
        System.out.println("一周中的第几天 "+ dt.getDayOfWeek());// 3

        System.out.println("如何取得从1970年1月1日到现在的毫秒数,第一种方法" + cal.getTimeInMillis());
        System.out.println("如何取得从1970年1月1日到现在的毫秒数,第二种方法" + System.currentTimeMillis());
        System.out.println("如何取得从1970年1月1日到现在的毫秒数,第三种方法" + Clock.systemDefaultZone().millis());*/
        System.out.println("如何取得某月的第一天" + getFirstDayOfMonth());
        System.out.println("如何取得某月的最后一天" + getLastDayOfMonth());
        System.out.println("当前时刻往前推一天" + getLastDayTimeOfNow());
    }
}
