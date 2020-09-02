package com.netposa.template.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 *
 * @author LP
 */
public class DateUtil {

    public static String formatFull = "yyyy-MM-dd HH:mm:ss";
    public static String formatSimple = "yyyy-MM-dd";
    public static String formatMonth = "yyyy-MM";
    public static String formatMinute = "HH:mm";
    public static String formatChineseSimple = "yyyy年MM月dd日";

    public static String getNowStr() {

        return formatDateToString(new Date(), formatFull);
    }

    public static boolean isNight(Date date) throws Exception {
        Date tempDate = new Date();
        DateFormat df1 = DateFormat.getDateInstance();
        System.out.println(df1.format(tempDate));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 23 || hour <= 5) {
            return true;
        }
        return false;
    }

    public static Date getInsuranceTime(Date date, Long num) throws Exception {
        Date tempDate = new Date();
        DateFormat df1 = DateFormat.getDateInstance();
        System.out.println(df1.format(tempDate));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, Math.toIntExact(num));
        return calendar.getTime();
    }

    public static Long getIssue(Date date) throws Exception {
        String dateString = DateFormatUtils.format(date, "yyyyMM");
        return Long.valueOf(dateString);
    }

    public static Long getIssue(String sdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatFull);
        Date date = null;
        try {
            date = sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long getIssue_scend(String sdate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatSimple);
        Date date = null;
        try {
            date = sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long getIsLong(String sdate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = sdf.parse(sdate);
        String dateString = DateFormatUtils.format(date, "yyyyMM");
        return Long.valueOf(dateString);
    }

    public static Long getNextIssue(Date date) throws Exception {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        String dateString = DateFormatUtils.format(cal.getTime(), "yyyyMM");
        return Long.valueOf(dateString);
    }

    public static Long getNextIssue(String dateStr) throws Exception {
        Date date = null;
        Calendar cal = Calendar.getInstance();
        if (dateStr == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            date = sdf.parse(dateStr);
            cal.setTime(date);
            cal.add(Calendar.MONTH, 1);
        } else {
            date = new Date();
            cal.setTime(date);
        }
        String dateString = DateFormatUtils.format(cal.getTime(), "yyyyMM");
        return Long.valueOf(dateString);
    }

    public static Date getNowDateStart() {
        Calendar currentDate = new GregorianCalendar();

        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        return currentDate.getTime();
    }

    /**
     * 获取某个日期的前后X天
     */
    public static Date getSomeDay(Date date, int x) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, x);
        return cal.getTime();
    }

    /**
     * 获取某个日期的前后X分钟
     */
    public static Date getSomeMinute(Date date, int x) {
        if (date == null) {
            date = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, x);
        return cal.getTime();
    }

    /**
     * 小时为HH是24小时制的，小时为hh是12小时制的yyyyMMddHHmmssSS
     *
     * @return
     */
    public static String getFullTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(date);
    }

    public static String getdateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 生成4位随机数
     */
    public static int getRandownBy4() {
        int result = (int) (Math.random() * (9999 - 1000) + 1000);
        return result;
    }

    /**
     * 生成6位随机数
     */
    public static int getRandownBy6() {
        int result = (int) (Math.random() * (999999 - 100000) + 100000);
        return result;
    }

    /**
     * 生成8位随机数
     */
    public static int getRandownBy8() {
        int result = (int) (Math.random() * (99999999 - 10000000) + 10000000);
        return result;
    }

    /**
     * 日期转字符串
     */
    public static String formatDateToString(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转日期
     */
    public static Date formatStringToDate(String date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当月的 天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 根据 年、月 获取对应的月份 的 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当月第一天
     */
    public static Date getTheMonthFirstDay() {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return cal_1.getTime();
    }

    /**
     * 获取今天几号
     */
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }


    public static void main(String[] args) throws Exception {
        System.out.println(getIssue("2020-02-02 18:00:00"));
    }

}
