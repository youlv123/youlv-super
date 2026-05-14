package com.ruoyi.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static String yyyyMMddTHHmmssSSS = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    public static String yyyyMMdd = "yyyyMMdd";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    private static final DateTimeFormatter yyyyMMdd_Formatter = DateTimeFormatter.ofPattern(yyyyMMdd);


    private static final DateTimeFormatter yyyyMMddTHHmmssSSS_Formatter = DateTimeFormatter.ofPattern(yyyyMMddTHHmmssSSS);

    private static final DateTimeFormatter YYYY_MM_DD_Formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate() {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime() {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow() {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format) {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date) {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date) {
        if (StringUtils.isBlank(format) || date == null) {
            return "";
        }
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts) {
        try {
            return new SimpleDateFormat(format).parse(ts);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime() {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算相差天数
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }

    /**
     * 计算时间差
     *
     * @param endTime   最后时间
     * @param startTime 开始时间
     * @return 时间差（天/小时/分钟）
     */
    public static String timeDistance(Date endTime, Date startTime) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endTime.getTime() - startTime.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 增加 LocalDateTime ==> Date
     */
    public static Date toDate(LocalDateTime temporalAccessor) {
        ZonedDateTime zdt = temporalAccessor.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 增加 LocalDate ==> Date
     */
    public static Date toDate(LocalDate temporalAccessor) {
        LocalDateTime localDateTime = LocalDateTime.of(temporalAccessor, LocalTime.of(0, 0, 0));
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    /**
     * 获得当前时间的业务日期
     *
     * @return
     */
    public static String getBizDate() {
        return getBizDate(0);
    }
/**
     * 获得指定偏移量的业务日期
     *
     * @param days 偏移量
     * @return 业务日期
     * 负数是前多少天,正数是后多少天
     */
    public static String getBizDate(int days) {
        // 1、获得指定偏移量的业务日期
        // 获取当前日期并加上偏移天数
        LocalDate targetDate = LocalDate.now().plusDays(days);
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        // 格式化日期
        String bizDate = targetDate.format(formatter);
        return bizDate;
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return 日期列表
     */
    public static List<String> getDaysBetweenDates(String startTime, String endTime) {
        // 参数校验
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("日期参数不能为空");
        }

        // 解析日期
        LocalDate startDate = LocalDate.parse(startTime, yyyyMMdd_Formatter);
        LocalDate endDate = LocalDate.parse(endTime, yyyyMMdd_Formatter);

        // 日期顺序校验
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("结束日期不能早于开始日期");
        }

        // 计算日期区间
        List<String> dates = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            dates.add(currentDate.format(yyyyMMdd_Formatter));
            currentDate = currentDate.plusDays(1);
        }

        return dates;
    }

    /**
     * 格式化日期 将yyyy-MM-dd'T'HH:mm:ss.SSS 转换为yyyyMMdd
     *
     * @param inputDate
     * @return
     */
    public static String formatDate(String inputDate) {

        // 解析输入字符串为 LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(inputDate, yyyyMMddTHHmmssSSS_Formatter);
        // 格式化为目标字符串
        return dateTime.format(yyyyMMdd_Formatter);
    }

    /**
     * 输入日期格式为yyyy-MM-dd 将yyyy-MM-dd转换为yyyyMMdd
     *
     * @param inputDate
     * @return
     */
    public static String formatDateYYYY_MM_DDToyyyyMMdd(String inputDate) {
        LocalDate date = LocalDate.parse(inputDate, YYYY_MM_DD_Formatter);
        return date.format(yyyyMMdd_Formatter);
    }


    public static long calculateDaysBetween(Date date) {
        // 将Date转换为LocalDate（忽略时间部分）
        LocalDate targetDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // 获取当前日期
        LocalDate currentDate = LocalDate.now();

        // 计算天数差（正数表示未来，负数表示过去）
//        return ChronoUnit.DAYS.between(currentDate, targetDate);
        return ChronoUnit.DAYS.between(targetDate, currentDate);
    }

    /**
     * 计算A-B的时间天数
     *
     * @param dateA
     * @param dateB
     * @return
     */
    public static long calculateDaysBetweenAB(Date dateA, Date dateB) {
        // 将Date转换为LocalDate（忽略时间部分）
        LocalDate targetDateA = dateA.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // 获取当前日期
        LocalDate targetDateB = dateB.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        // 计算天数差（正数表示未来，负数表示过去）
        return ChronoUnit.DAYS.between(targetDateA, targetDateB);
    }


    /**
     * 计算复利增长的最终总额
     *
     * @param initialPrincipal 初始本金（例如：700000）
     * @param monthlyDeposit   每月追加投资（例如：11000）
     * @param annualRate       年化利率（例如：0.04 表示 4%）
     * @param years            投资年限（例如：5）
     * @return 最终总额
     */
    public static double calculateCompoundInterest(double initialPrincipal,
                                                   double monthlyDeposit,
                                                   double annualRate,
                                                   int years) {
        double monthlyRate = annualRate / 12; // 月利率
        int months = years * 12;              // 总月数
        double total = initialPrincipal;      // 初始本金

        for (int i = 0; i < months; i++) {
            // 每月初追加投资
            total += monthlyDeposit;
            // 计算当月利息并累加到总额
            total *= (1 + monthlyRate);
        }

        return total;
    }

    /**
     * 将毫秒数转换成时分秒
     *
     * @param millis 毫秒数
     * @return 格式化后的字符串
     */
    public static String formatMillisWithPadding(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("毫秒值不能为负数");
        }
        // 如果不足1秒，直接返回毫秒
        if (millis < 1000) {
            return millis + "毫秒";
        }

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        long remainingMillis = millis - TimeUnit.HOURS.toMillis(hours);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(remainingMillis);
        remainingMillis -= TimeUnit.MINUTES.toMillis(minutes);

        long seconds = TimeUnit.MILLISECONDS.toSeconds(remainingMillis);

        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        if (minutes > 0) {
            sb.append(minutes).append("分");
        }
        if (seconds > 0 || (hours == 0 && minutes == 0)) {
            if (minutes > 0 && seconds < 10) {
                sb.append("0"); // 补零（如 "1分05秒"）
            }
            sb.append(seconds).append("秒");
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        double result = calculateCompoundInterest(700000, 15000, 0.04, 1);
        System.out.printf("5年后的总额为: %.2f 元%n", result);
        // 输出: 5年后的总额为: 1588000.00 元
    }

}
