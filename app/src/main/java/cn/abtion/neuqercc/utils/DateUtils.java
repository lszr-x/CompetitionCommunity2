package cn.abtion.neuqercc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author FanHongyu.
 * @since 18/3/21 18:37.
 * email fanhongyu@hrsoft.net.
 */

public class DateUtils {

    /**
     * 一分钟秒数
     */
    public static final int SECOND_OF_MINUTE = 60;
    /**
     * 一小时分钟数
     */
    public static final int MINUTE_OF_HOUR = 60;
    /**
     * 一天小时数
     */
    public static final int HOUR_OF_DAY = 24;

    /**
     * 一周天数
     */
    public static final int DAY_OF_WEEK = 7;

    /**
     * 小月份天数
     */
    public static final int DAY_OF_SMALL_MONTH = 30;
    /**
     * 大月份天数
     */
    public static final int DAY_OF_BIG_MONTH = 31;
    /**
     * 平年二月天数
     */
    public static final int DAY_OF_AVERAGE_TEAR_FEBRUARY = 28;
    /**
     * 闰年二月天数
     */
    public static final int DAY_OF_LEAP_TEAR_FEBRUARY = 29;


    /**
     * 一年月数
     */
    public static final int MONTH_OF_YEAR = 12;

    /**
     * 一个世纪年数
     */
    public static final int YEAR_OF_CENTURY = 100;
    /**
     * 四个世纪年数
     */
    public static final int YEAR_OF_FOUR_CENTURY = 400;


    /**
     * 1月到12月
     */
    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    /**
     * 默认日期格式
     */
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    /**
     * 默认时间格式
     */
    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";
    /**
     * 默认日期时间格式
     */
    public static final String DATE_TIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final int FOUR_YEARS_INTERVAL = 4;



    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }



    /**
     * @return 当天的年月日
     */
    public static String getCurrentDate() {
        SimpleDateFormat yyyyMmDdFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT, Locale.getDefault());
        return yyyyMmDdFormat.format(new Date());
    }

    /**
     * @return 当前的时分秒
     */
    public static String getCurrentTime() {
        SimpleDateFormat hhMmSsFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT, Locale.getDefault());
        return hhMmSsFormat.format(new Date());
    }


    /**
     * @return 完整的年月日时分秒
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat yyyyMmDdHhMmSs = new SimpleDateFormat(DATE_TIME_DEFAULT_FORMAT, Locale.getDefault());
        return yyyyMmDdHhMmSs.format(new Date());
    }

    /**
     * @param format 指定格式
     * @return 当天的年月日
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat yyyyMmDdFormat = new SimpleDateFormat(format, Locale.getDefault());
        return yyyyMmDdFormat.format(new Date());
    }

    /**
     *
     * @param format 指定格式
     * @return 当前的时分秒
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat hhMmSsFormat = new SimpleDateFormat(format, Locale.getDefault());
        return hhMmSsFormat.format(new Date());
    }


    /**
     * @param format 指定格式
     * @return 完整的年月日时分秒
     */
    public static String getCurrentDateTime(String format) {
        SimpleDateFormat yyyyMmDdHhMmSs = new SimpleDateFormat(format, Locale.getDefault());
        return yyyyMmDdHhMmSs.format(new Date());
    }

    /**
     * 从特定格式日期中获取年份
     *
     * @param dateTime   时间串
     * @param dateFormat 格式
     * @return 年份
     */
    public static int getYear(String dateTime, String dateFormat) {

        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
            Date date = simpleDateFormat.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * @param date Date类型
     * @return 年
     */
    public static int getYear(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 从特定格式日期中获取月份
     *
     * @param dateTime   时间串
     * @param dateFormat 格式
     * @return 月份
     */
    public static int getMonth(String dateTime, String dateFormat) {

        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
            Date date = simpleDateFormat.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * @param date Date类型
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }


    /**
     * 从特定格式日期中获取天
     *
     * @param dateTime   时间串
     * @param dateFormat 格式
     * @return 天
     */
    public static int getDay(String dateTime, String dateFormat) {

        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
            Date date = simpleDateFormat.parse(dateTime);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * @param date Date类型
     * @return 月份中的第几天
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * @param date Date类型
     * @return 星期几(周日到周六)
     */
    public static int getWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 判断是否为闰年
     *
     * @param year 年份
     * @return 是否为闰年
     */
    public boolean isLeapYear(int year) {

        if (year % YEAR_OF_FOUR_CENTURY == 0) {
            return true;
        } else if (year % FOUR_YEARS_INTERVAL == 0 && year % YEAR_OF_CENTURY != 0) {
            return true;
        }
        return false;
    }


    /**
     * 获取某个月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    public int getDayOfMonth(int year, int month) {
        if (year < JANUARY || year > DECEMBER) {
            return 0;
        }

        boolean isLeapYear = isLeapYear(year);
        int dayOfMonth = 0;

        switch (month) {
            case JANUARY:
            case MARCH:
            case MAY:
            case JULY:
            case AUGUST:
            case OCTOBER:
            case DECEMBER:
                dayOfMonth = DAY_OF_BIG_MONTH;
                break;
            case APRIL:
            case JUNE:
            case SEPTEMBER:
            case NOVEMBER:
                dayOfMonth = DAY_OF_SMALL_MONTH;
                break;
            case FEBRUARY:
                if (isLeapYear) {
                    dayOfMonth = DAY_OF_LEAP_TEAR_FEBRUARY;
                } else {
                    dayOfMonth = DAY_OF_AVERAGE_TEAR_FEBRUARY;
                }
            default:
                break;

        }
        return dayOfMonth;
    }

    /**
     * 获取当前系统时间戳
     *
     * @return 当前时间戳
     */
    public static long getCurrentTimeMills() {
        return System.currentTimeMillis();
    }


    /**
     * 获取指定日期时间戳
     *
     * @param date 特定日期
     * @return 特定日期时间戳
     */
    public static long getTimeMills(Date date) {
        return date.getTime();
    }


    /**
     * 获取特定格式日期时间戳
     *
     * @param dateTime   特定日期
     * @param dateFormat 对应格式
     * @return 时间戳
     */
    public static long getTimeMills(String dateTime, String dateFormat) {

        if (dateTime == null || dateFormat == null) {
            return 0;
        }
        try {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
            Date date = simpleDateFormat.parse(dateTime);
            return date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
