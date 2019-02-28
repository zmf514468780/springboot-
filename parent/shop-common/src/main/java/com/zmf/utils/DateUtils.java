package com.zmf.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 10:13
 * @Description:
 */
public class DateUtils {
    /**
     *  时分秒，如果HH 大写则为24小时制，hh 为12小时制度
     */
    public static String DATE_TO_STRING_DEFAULT_PATTERN="yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日
     */
    public  static final String DATE_TO_STRING_SHORT_PATTERN="yyyy-MM-dd";


    private static SimpleDateFormat simpleDateFormat;

    /**
     *  给定时间、格式 转换成string
     * @param source 时间
     * @param pattern 格式
     * @return
     */
    public  static String dateToString(Date source, String pattern){
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }

    /**
     * unix 时间搓转换为指定的string 类型
     *
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public  static String timeStampToString(long source,String pattern){
        simpleDateFormat  = new SimpleDateFormat(pattern);
        Date date = new Date(source *1000);
        return simpleDateFormat.format(date);
    }

    /**
     *  将日期转换为时间戳(unix时间戳,单位秒)
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime()/1000;
    }


    /**
     *  String 转date
     * @param source
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String source,String pattern) throws ParseException {
        simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        date = simpleDateFormat.parse(source);
        return date;
    }


    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern) {
        simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     *
     * @methodDesc: 功能描述:(获取当前系统时间戳)
     * @param: @return
     */
    public static Timestamp getTimestamp() {
        return new Timestamp(new Date().getTime());
    }









}
