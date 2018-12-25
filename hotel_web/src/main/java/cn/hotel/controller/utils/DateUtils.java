package cn.hotel.controller.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期转换工具类
 */
public class DateUtils {

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FORMAT_FULL_STANDARD = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT_FULL_ONLYNUM = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String FORMAT_FULL_ONLYNUM_MS = "yyyyMMddHHmmssSSS";
    /**
     * yyyyMMddHHmm
     */
    public static final String FORMAT_MINUTE_ONLYNUM ="yyyyMMddHHmm";
    /**
     * yyyy-MM-dd
     */
    public static final String FORMAT_DATE_STANDARD = "yyyy-MM-dd";
    /**
     * yyyyMMdd
     */
    public static final String FORMAT_DATA_ONLYNUM = "yyyyMMdd";
    /**
     * HH:mm:ss
     */
    public static final String FORMAT_TIME_STANDARD = "HH:mm:ss";
    /**
     * HHmmss
     */
    public static final String FORMAT_TIME_ONLYNUM = "HHmmss";

    /**
     * 格式化日期
     * @param ldt
     * @param format
     * @return
     */
    public static String dataFormat(final LocalDateTime ldt, final String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return ldt.format(formatter);
    }

    /**
     * 获取当前完整日期时间yyyy-MM-dd HH:mm:ss
     * @return
     * @param format
     */
    public static String getCurrFullDataStandard(String format) {
        return dataFormat(LocalDateTime.now(), FORMAT_FULL_STANDARD);
    }

    /**
     * 获取当前数字日期时间yyyyMMddHHmmss
     * @return
     */
    public static String getCurrFullDateOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_FULL_ONLYNUM);
    }

    /**
     * 获取当前完整日期yyyy-MM-dd
     * @return
     */
    public static String getCurrDataStandard() {
        return dataFormat(LocalDateTime.now(), FORMAT_DATE_STANDARD);
    }
    /**
     * 获取当前数字日期yyyyMMdd
     * @return
     */
    public static String getCurrDateOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_DATA_ONLYNUM);
    }

    /**
     * 获取当前数字日期yyyyMMddHHmm
     * @return
     */
    public static String getCurrMinuteDateOnlyNum() {return dataFormat(LocalDateTime.now(), FORMAT_MINUTE_ONLYNUM);}

    /**
     * 获取当前完整时间HH:mm:ss
     * @return
     */
    public static String getCurrTimeStandard() {
        return dataFormat(LocalDateTime.now(), FORMAT_TIME_STANDARD);
    }

    /**
     * 获取当前数字时间HHmmss
     * @return
     */
    public static String getCurrTimeOnlyNum() {
        return dataFormat(LocalDateTime.now(), FORMAT_TIME_ONLYNUM);
    }

    /**
     * yyyy-MM-dd格式string转换LocalDate
     * @param date
     * @return
     */
    public static LocalDate getLocalDateByString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE_STANDARD);
        return LocalDate.parse(date, formatter);
    }

    /**
     * string转换LocalDate
     * @return
     */
    public static LocalDate getLocalDateByStringAndPattern(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(datetime, formatter);
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式string转换LocalDateTime
     * @param datetime
     * @return
     */
    public static LocalDateTime getLocalDateTimeByString(String datetime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FULL_STANDARD);
        return LocalDateTime.parse(datetime, formatter);
    }

    public static LocalDateTime getLocalFullDateTimeByString(String datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_FULL_ONLYNUM);
        return LocalDateTime.parse(datetime, formatter);
    }

    public static LocalDateTime getLocalFullDateTimeByPattern(String datetime, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetime, formatter);
    }

    /**
     * 获取当前毫秒数
     * @return
     */
    public static Long getCurrMilli() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    public static Long getCurrDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatter.parse(getCurrDataStandard());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 获取当月1日0点的毫秒数
     * @return
     */
    public static Long getCurrMonth() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        try {
            date = formatter.parse(getCurrDataStandard());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static Long getLongByString(String datetime) {
        return Timestamp.valueOf(DateUtils.getLocalDateTimeByString(datetime)).getTime();
    }

    public static Long getLongByDateString(String date) {
        date = date + " 00:00:00";
        return Timestamp.valueOf(DateUtils.getLocalDateTimeByString(date)).getTime();
    }

    public static Long getLongByLdt(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt).getTime();
    }

    public static Long getStartOfDay(LocalDateTime now){
        LocalDate ld = now.toLocalDate();
        LocalDateTime ldt = ld.atStartOfDay();
        return getLongByLdt(ldt);
    }

    public static Long getEndOfDay(LocalDateTime now){
        LocalDate ld = now.toLocalDate();
        ld = ld.plusDays(1l);
        LocalDateTime ldt = ld.atStartOfDay();
        return getLongByLdt(ldt);
    }

    public static Long getEndOfDay(Long now){
        Date nowDate = new Date(now);
        Calendar nowC = Calendar.getInstance();
        nowC.setTime(nowDate);
        nowC.add(Calendar.DAY_OF_MONTH,1);
        nowC.set(Calendar.HOUR_OF_DAY,0);
        nowC.set(Calendar.MINUTE,0);
        nowC.set(Calendar.SECOND,0);
        nowC.set(Calendar.MILLISECOND,0);
        return nowC.getTime().getTime();
    }

    public static Long getEndInterestDate(Long startInterestDate, int termCount,int termUnit) {
        Long endInterestDate = 0L;
        if (termUnit == 3) {
            endInterestDate = DateUtils.getNMonthDate(startInterestDate, termCount);
        }
        if (termUnit == 1) {
            endInterestDate = DateUtils.getNDayToDate(startInterestDate, termCount-1);
        }
        return endInterestDate;
    }

    public static Long getNMonthDate(Long date, int n) {
        Date d = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.MONTH, n);
        return calendar.getTime().getTime();
    }

    public static Long getNDayToDate(Long date ,int n) {
        Date d = new Date(date);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        c.add(Calendar.DAY_OF_MONTH, n);
        return c.getTime().getTime();
    }

    public static String formatByDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(long time,String format){
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }

    /**
     * 向后推进month个月
     * @param month
     * @return
     */
    public static String getDate(int month){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(GregorianCalendar.MONTH,month);
        String begin = new java.sql.Date(calendar.getTime().getTime())
                .toString();
        return begin;
    }

    /**
     * 获取n天前日期yyyyMMdd（如n==1,则返回昨天）
     * @param n
     * @return
     */
    public static String getBefNDate(int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day-n);

        return new SimpleDateFormat(FORMAT_DATA_ONLYNUM).format(c.getTime());
    }

    /**
     * 时间区间比较startTime < currentTime && currentTime <= endTime
     * Discription:[方法功能中文描述];
     * Created on 2017年9月13日
     * @param startTime HH:mm:ss
     * @param endTime HH:mm:ss
     * @return
     */
    public static boolean currmilliCompare(String startTime,String endTime){
        boolean flag = false;
        long currentTimeNum = DateUtils.getCurrMilli();
        String currentDate = DateUtils.getCurrDataStandard();
        long startTimeNum = DateUtils.getLongByString(currentDate+" "+startTime);
        long endTimeNum = DateUtils.getLongByString(currentDate+" "+endTime);
        if(startTimeNum < currentTimeNum && currentTimeNum <= endTimeNum){
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
//		String currentDate = DateUtils.getCurrDataStandard();
//		System.out.println("当前日期:"+currentDate);
//
//		long currentTimeNum = DateUtils.getCurrMilli();
//		System.out.println("当前时间:"+currentTimeNum);
//		String startTime = "09:30:00";
//		long startTimeNum = DateUtils.getLongByString(currentDate+" "+startTime);
//	    System.out.println("开始时间:"+startTimeNum);
//	    String endTime = "17:30:00";
//	    long endTimeNum = DateUtils.getLongByString(currentDate+" "+endTime);
//	    System.out.println("结束时间:"+endTimeNum);
//	    boolean falg = false;
//	    if(startTimeNum < currentTimeNum && currentTimeNum <= endTimeNum){
//	    	falg = true;
//	    }
//	    System.out.println(falg);
        boolean flag = currmilliCompare("09:30:00", "17:30:00");
        System.out.println(flag);
    }
}