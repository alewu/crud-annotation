package com.ale.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class DateTimeDemo {
    @Test
    public void test() {
        Period period = Period.between(LocalDate.parse("2010-05-14"), LocalDate.parse("2011-03-14"));
        log.info("年：[{}]", period.getYears());
        log.info("月：[{}]", period.getMonths());
        log.info("日：[{}]", period.getDays());

        Duration duration = Duration.between(LocalDateTime.parse("2018-01-18T06:30"), LocalDateTime.parse
                ("2018-02-14T22:58"));
        log.info("天数：[{}]", duration.toDays());
        log.info("小时：[{}]", duration.toHours());

        log.info("相差天数[{}]", ChronoUnit.DAYS.between(LocalDate.parse("2018-01-18"), LocalDate.parse("2018-01-20")));

        LocalDateTime firstDayOfMonth = LocalDateTime.now().with(TemporalAdjusters.firstDayOfMonth());
        long startBetween = ChronoUnit.DAYS.between(LocalDateTime.now(), firstDayOfMonth);

        log.info("startBetween[{}], firstDayOfMonth[{}]", startBetween, firstDayOfMonth);

//        在Java 8中判断两个日期是否相等
//        时间：2016-12-27发布：广州java培训来源：达内新闻分享到：
//        在Java 8中判断两个日期是否相等
//
//        现 实生活中有一类时间处理就是判断两个日期是否相等。你常常会检查今天是不是个特殊的日子，比如生日、纪念日或非交易日。这时就需要把指定的日期与某个特定
// 日期做比较，例如判断这一天是否是假期。下面这个例子会帮助你用Java 8的方式去解决，你肯定已经想到了，LocalDate重载了equal方法，请看下面的例子：
        LocalDate date1 = LocalDate.of(2018, 5, 22);

        LocalDate obj = LocalDateTime.now().toLocalDate();
        if (date1.equals(obj)) {
            System.out.printf("Today %s and date1 %s are same date %n", obj, date1);

        }

    }
}
