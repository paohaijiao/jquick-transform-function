/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
package com.github.paohaijiao.date;

/**
 * packageName com.github.paohaijiao.date
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) [2025-2099] Martin (goudingcheng@gmail.com)
 */
import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Date 日期函数提供者测试类
 * 测试范围：日期运算、日期差、日期提取、日期比较、日期边界等
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionDateProviderTest {

    private JQuickMethodInvocationManager manager;
    private LocalDate testDate;
    private LocalDateTime testDateTime;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
        testDate = LocalDate.of(2024, 5, 10);
        testDateTime = LocalDateTime.of(2024, 5, 10, 15, 30, 44);
    }

    @Test
    public void testAddDays() {
        // LocalDate 增加天数
        LocalDate result = (LocalDate) manager.invoke("addDays", Arrays.asList(testDate, 5));
        assertEquals(LocalDate.of(2024, 5, 15), result);
        // 负数天数
        result = (LocalDate) manager.invoke("addDays", Arrays.asList(testDate, -3));
        assertEquals(LocalDate.of(2024, 5, 7), result);

        // LocalDateTime 增加天数
        LocalDateTime dateTimeResult = (LocalDateTime) manager.invoke("addDays", Arrays.asList(testDateTime, 5));
        assertEquals(LocalDateTime.of(2024, 5, 15, 15, 30, 45), dateTimeResult);

        // 字符串日期
        result = (LocalDate) manager.invoke("addDays", Arrays.asList("2024-05-10", 7));
        assertEquals(LocalDate.of(2024, 5, 17), result);
    }

    @Test
    public void testAddHours() {
        // LocalDateTime 增加小时
        LocalDateTime result = (LocalDateTime) manager.invoke("addHours", Arrays.asList(testDateTime, 5));
        assertEquals(LocalDateTime.of(2024, 5, 10, 20, 30, 45), result);

        // 负数小时
        result = (LocalDateTime) manager.invoke("addHours", Arrays.asList(testDateTime, -3));
        assertEquals(LocalDateTime.of(2024, 5, 10, 12, 30, 45), result);

        // 跨天
        result = (LocalDateTime) manager.invoke("addHours", Arrays.asList(testDateTime, 10));
        assertEquals(LocalDateTime.of(2024, 5, 11, 1, 30, 45), result);

        // 字符串日期时间
        result = (LocalDateTime) manager.invoke("addHours", Arrays.asList("2024-05-10T15:30:45", 2));
        assertEquals(LocalDateTime.of(2024, 5, 10, 17, 30, 45), result);
    }

    @Test
    public void testAddMinutes() {
        // LocalDateTime 增加分钟
        LocalDateTime result = (LocalDateTime) manager.invoke("addMinutes", Arrays.asList(testDateTime, 30));
        assertEquals(LocalDateTime.of(2024, 5, 10, 16, 0, 45), result);

        // 负数分钟
        result = (LocalDateTime) manager.invoke("addMinutes", Arrays.asList(testDateTime, -15));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 15, 45), result);

        // 跨小时
        result = (LocalDateTime) manager.invoke("addMinutes", Arrays.asList(testDateTime, 45));
        assertEquals(LocalDateTime.of(2024, 5, 10, 16, 15, 45), result);
    }

    @Test
    public void testAddSeconds() {
        // LocalDateTime 增加秒
        LocalDateTime result = (LocalDateTime) manager.invoke("addSeconds", Arrays.asList(testDateTime, 15));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 59), result);
        // 负数秒
        result = (LocalDateTime) manager.invoke("addSeconds", Arrays.asList(testDateTime, -10));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 34), result);
    }

    @Test
    public void testAddMonths() {
        // LocalDate 增加月份
        LocalDate result = (LocalDate) manager.invoke("addMonths", Arrays.asList(testDate, 3));
        assertEquals(LocalDate.of(2024, 8, 10), result);
        // 负数月份
        result = (LocalDate) manager.invoke("addMonths", Arrays.asList(testDate, -2));
        assertEquals(LocalDate.of(2024, 3, 10), result);

        // 跨年
        result = (LocalDate) manager.invoke("addMonths", Arrays.asList(testDate, 8));
        assertEquals(LocalDate.of(2025, 1, 10), result);

        // 月末处理（1月31日加1个月到2月28日）
        result = (LocalDate) manager.invoke("addMonths", Arrays.asList(LocalDate.of(2024, 1, 31), 1));
        assertEquals(LocalDate.of(2024, 2, 29), result); // 2024年是闰年
    }

    @Test
    public void testAddYears() {
        // LocalDate 增加年份
        LocalDate result = (LocalDate) manager.invoke("addYears", Arrays.asList(testDate, 1));
        assertEquals(LocalDate.of(2025, 5, 10), result);

        // 负数年份
        result = (LocalDate) manager.invoke("addYears", Arrays.asList(testDate, -1));
        assertEquals(LocalDate.of(2023, 5, 10), result);

        // 闰年2月29日
        result = (LocalDate) manager.invoke("addYears", Arrays.asList(LocalDate.of(2024, 2, 29), 1));
        assertEquals(LocalDate.of(2025, 2, 28), result);
    }
    @Test
    public void testAge() {
        LocalDate birthDate = LocalDate.of(1990, 3, 7);
        LocalDate refDate = LocalDate.of(2024, 1, 1);
        int age = (int) manager.invoke("age", Arrays.asList(birthDate, refDate));
        assertEquals(33, age);
        // 使用当前日期
        int currentAge = (int) manager.invoke("age", Arrays.asList(birthDate));
        assertTrue(currentAge >= 34);
    }

    @Test
    public void testDaysBetween() {
        LocalDate date1 = LocalDate.of(2024, 5, 1);
        LocalDate date2 = LocalDate.of(2024, 5, 10);

        long days = (long) manager.invoke("daysBetween", Arrays.asList(date1, date2));
        assertEquals(9, days);

        // 顺序无关（返回绝对值）
        days = (long) manager.invoke("daysBetween", Arrays.asList(date2, date1));
        assertEquals(9, days);
    }

    @Test
    public void testHoursBetween() {
        LocalDateTime dt1 = LocalDateTime.of(2024, 5, 10, 8, 0, 0);
        LocalDateTime dt2 = LocalDateTime.of(2024, 5, 10, 17, 0, 0);
        long hours = (long) manager.invoke("hoursBetween", Arrays.asList(dt1, dt2));
        assertEquals(9, hours);
    }

    @Test
    public void testMonthsBetween() {
        LocalDate date1 = LocalDate.of(2024, 1, 15);
        LocalDate date2 = LocalDate.of(2024, 6, 10);

        long months = (long) manager.invoke("monthsBetween", Arrays.asList(date1, date2));
        assertEquals(4, months);
    }

    @Test
    public void testYearsBetween() {
        LocalDate date1 = LocalDate.of(2020, 5, 10);
        LocalDate date2 = LocalDate.of(2024, 5, 10);
        long years = (long) manager.invoke("yearsBetween", Arrays.asList(date1, date2));
        assertEquals(4, years);
    }
    @Test
    public void testYear() {
        // 无参数 - 当前年份
        int year = (int) manager.invoke("year", Arrays.asList());
        assertEquals(LocalDate.now().getYear(), year);

        // 有参数 - LocalDate
        year = (int) manager.invoke("year", Arrays.asList(testDate));
        assertEquals(2024, year);

        // LocalDateTime
        year = (int) manager.invoke("year", Arrays.asList(testDateTime));
        assertEquals(2024, year);

        // 字符串
        year = (int) manager.invoke("year", Arrays.asList("2024-05-10"));
        assertEquals(2024, year);
    }

    @Test
    public void testMonth() {
        // 无参数 - 当前月份
        int month = (int) manager.invoke("month", Arrays.asList());
        assertEquals(LocalDate.now().getMonthValue(), month);

        month = (int) manager.invoke("month", Arrays.asList(testDate));
        assertEquals(5, month);
    }

    @Test
    public void testDay() {
        // 无参数 - 当前日
        int day = (int) manager.invoke("day", Arrays.asList());
        assertEquals(LocalDate.now().getDayOfMonth(), day);
        day = (int) manager.invoke("day", Arrays.asList(testDate));
        assertEquals(10, day);
    }

    @Test
    public void testDayOfWeek() {
        // 无参数 - 返回数字
        int dow = (int) manager.invoke("dayOfWeek", Arrays.asList());
        assertTrue(dow >= 1 && dow <= 7);
        // 返回数字 周一=1
        dow = (int) manager.invoke("dayOfWeek", Arrays.asList(testDate));
        assertEquals(5, dow); // 2024-05-10 是周五，周五=5
        // 返回名称
        String dowName = (String) manager.invoke("dayOfWeek", Arrays.asList(testDate, "name"));
        assertEquals("星期五", dowName);
    }

    @Test
    public void testDayOfYear() {
        // 无参数 - 当前年中第几天
        int doy = (int) manager.invoke("dayOfYear", Arrays.asList());
        assertTrue(doy >= 1 && doy <= 366);
        doy = (int) manager.invoke("dayOfYear", Arrays.asList(testDate));
        // 2024-05-10 是第 31+29+31+30+10 = 131 天
        assertEquals(131, doy);
    }

    @Test
    public void testWeekOfYear() {
        // 无参数 - 当前周
        int week = (int) manager.invoke("weekOfYear", Arrays.asList());
        assertTrue(week >= 1 && week <= 53);

        week = (int) manager.invoke("weekOfYear", Arrays.asList(testDate));
        System.out.println("2024-05-10 是第 " + week + " 周");
        assertTrue(week >= 18 && week <= 20);
    }

    @Test
    public void testHour() {
        // 无参数 - 当前小时
        int hour = (int) manager.invoke("hour", Arrays.asList());
        assertTrue(hour >= 0 && hour <= 23);

        hour = (int) manager.invoke("hour", Arrays.asList(testDateTime));
        assertEquals(15, hour);
    }

    @Test
    public void testMinute() {
        int minute = (int) manager.invoke("minute", Arrays.asList(testDateTime));
        assertEquals(30, minute);
    }

    @Test
    public void testSecond() {
        int second = (int) manager.invoke("second", Arrays.asList(testDateTime));
        assertEquals(45, second);
    }
    @Test
    public void testIsAfter() {
        LocalDate date1 = LocalDate.of(2024, 5, 15);
        LocalDate date2 = LocalDate.of(2024, 5, 10);

        assertTrue((boolean) manager.invoke("isAfter", Arrays.asList(date1, date2)));
        assertFalse((boolean) manager.invoke("isAfter", Arrays.asList(date2, date1)));
    }

    @Test
    public void testIsBefore() {
        LocalDate date1 = LocalDate.of(2024, 5, 5);
        LocalDate date2 = LocalDate.of(2024, 5, 10);
        assertTrue((boolean) manager.invoke("isBefore", Arrays.asList(date1, date2)));
        assertFalse((boolean) manager.invoke("isBefore", Arrays.asList(date2, date1)));
    }

    @Test
    public void testIsSameDay() {
        LocalDate date1 = LocalDate.of(2024, 5, 10);
        LocalDate date2 = LocalDate.of(2024, 5, 10);
        LocalDate date3 = LocalDate.of(2024, 5, 11);
        assertTrue((boolean) manager.invoke("isSameDay", Arrays.asList(date1, date2)));
        assertFalse((boolean) manager.invoke("isSameDay", Arrays.asList(date1, date3)));
    }

    @Test
    public void testIsDate() {
        // 有效日期类型
        assertTrue((boolean) manager.invoke("isDate", Arrays.asList(testDate)));
        assertTrue((boolean) manager.invoke("isDate", Arrays.asList(testDateTime)));
        assertTrue((boolean) manager.invoke("isDate", Arrays.asList(new Date())));

        // 有效日期字符串
        assertTrue((boolean) manager.invoke("isDate", Arrays.asList("2024-05-10")));
        // 无效日期
        assertFalse((boolean) manager.invoke("isDate", Arrays.asList("invalid")));
        assertFalse((boolean) manager.invoke("isDate", Arrays.asList("2024-13-10")));
        assertFalse((boolean) manager.invoke("isDate", Arrays.asList(123)));
    }

    @Test
    public void testIsLeapYear() {
        // 无参数 - 当前年份
        boolean isLeap = (boolean) manager.invoke("isLeapYear", Arrays.asList());
        System.out.println("当前年份是否闰年: " + isLeap);
        // 闰年
        assertTrue((boolean) manager.invoke("isLeapYear", Arrays.asList(2024)));
        assertTrue((boolean) manager.invoke("isLeapYear", Arrays.asList(2000)));
        // 平年
        assertFalse((boolean) manager.invoke("isLeapYear", Arrays.asList(2023)));
        assertFalse((boolean) manager.invoke("isLeapYear", Arrays.asList(1900)));
    }

    @Test
    public void testIsWeekend() {
        // 周六
        assertTrue((boolean) manager.invoke("isWeekend", Arrays.asList(LocalDate.of(2024, 5, 11))));
        // 周日
        assertTrue((boolean) manager.invoke("isWeekend", Arrays.asList(LocalDate.of(2024, 5, 12))));
        // 工作日
        assertFalse((boolean) manager.invoke("isWeekend", Arrays.asList(testDate)));
    }
    @Test
    public void testStartOfDay() {
        // 无参数 - 当前日期的开始
        LocalDateTime result = (LocalDateTime) manager.invoke("startOfDay", Arrays.asList());
        assertEquals(LocalDate.now().atStartOfDay(), result);
        result = (LocalDateTime) manager.invoke("startOfDay", Arrays.asList(testDate));
        assertEquals(LocalDateTime.of(2024, 5, 10, 0, 0, 0), result);
    }

    @Test
    public void testEndOfDay() {
        LocalDateTime result = (LocalDateTime) manager.invoke("endOfDay", Arrays.asList(testDate));
        assertEquals(LocalDateTime.of(2024, 5, 10, 23, 59, 59, 999999999), result);
    }

    @Test
    public void testStartOfMonth() {
        LocalDate result = (LocalDate) manager.invoke("startOfMonth", Arrays.asList(testDate));
        assertEquals(LocalDate.of(2024, 5, 1), result);
    }

    @Test
    public void testEndOfMonth() {
        LocalDate result = (LocalDate) manager.invoke("endOfMonth", Arrays.asList(testDate));
        assertEquals(LocalDate.of(2024, 5, 31), result);
        // 2月闰年
        result = (LocalDate) manager.invoke("endOfMonth", Arrays.asList(LocalDate.of(2024, 2, 10)));
        assertEquals(LocalDate.of(2024, 2, 29), result);
    }

    @Test
    public void testStartOfYear() {
        LocalDate result = (LocalDate) manager.invoke("startOfYear", Arrays.asList(testDate));
        assertEquals(LocalDate.of(2024, 1, 1), result);
    }

    @Test
    public void testEndOfYear() {
        LocalDate result = (LocalDate) manager.invoke("endOfYear", Arrays.asList(testDate));
        assertEquals(LocalDate.of(2024, 12, 31), result);
    }
    @Test
    public void testNow() {
        LocalDateTime now = (LocalDateTime) manager.invoke("now", Arrays.asList());
        assertNotNull(now);
    }

    @Test
    public void testToday() {
        LocalDate today = (LocalDate) manager.invoke("today", Arrays.asList());
        assertEquals(LocalDate.now(), today);
    }

    @Test
    public void testTimestamp() {
        long timestamp = (long) manager.invoke("timestamp", Arrays.asList());
        assertTrue(timestamp > 0);
    }

    @Test
    public void testParseDate() {
        // 无格式 - ISO 格式
        LocalDateTime result = (LocalDateTime) manager.invoke("parseDate", Arrays.asList("2024-05-10T15:30:45"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 45), result);
        // 带格式
        result = (LocalDateTime) manager.invoke("parseDate", Arrays.asList("10/05/2024 15:30:45", "dd/MM/yyyy HH:mm:ss"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 45), result);
    }

    @Test
    public void testFormatDate() {
        // 1个参数 - 默认格式
        String result = (String) manager.invoke("formatDate", Arrays.asList(testDateTime));
        assertEquals(testDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), result);
        // 2个参数 - 自定义格式
        result = (String) manager.invoke("formatDate", Arrays.asList(testDate, "yyyy年MM月dd日"));
        assertEquals("2024年05月10日", result);
        result = (String) manager.invoke("formatDate", Arrays.asList(testDateTime, "yyyy-MM-dd"));
        assertEquals("2024-05-10", result);
    }

    @Test
    public void testToIsoString() {
        // LocalDate
        String result = (String) manager.invoke("toIsoString", Arrays.asList(testDate));
        assertEquals("2024-05-10", result);
        // LocalDateTime
        result = (String) manager.invoke("toIsoString", Arrays.asList(testDateTime));
        assertTrue(result.startsWith("2024-05-10T"));
    }
    @Test
    public void testDateCalculationChain() {
        // 日期计算链：当前日期 -> 加30天 -> 格式化
        LocalDate date = (LocalDate) manager.invoke("today", Arrays.asList());
        LocalDate after30Days = (LocalDate) manager.invoke("addDays", Arrays.asList(date, 30));
        String formatted = (String) manager.invoke("formatDate", Arrays.asList(after30Days, "yyyy-MM-dd"));
        System.out.println("30天后: " + formatted);
        assertNotNull(formatted);
    }

    @Test
    public void testDaysUntilBirthday() {
        LocalDate today = (LocalDate) manager.invoke("today", Arrays.asList());
        LocalDate birthday = LocalDate.of(today.getYear(), 12, 25);
        if (birthday.isBefore(today)) {
            birthday = birthday.plusYears(1);
        }
        long daysUntil = (long) manager.invoke("daysBetween", Arrays.asList(today, birthday));
        System.out.println("距离圣诞节还有 " + daysUntil + " 天");
        assertTrue(daysUntil >= 0);
    }

    @Test
    public void testAgeCalculationScenario() {
        // 年龄计算场景
        String birthStr = "1990-03-07";
        LocalDate birthDate = (LocalDate) manager.invoke("parseDate", Arrays.asList(birthStr + "T00:00:00"));

        int age = (int) manager.invoke("age", Arrays.asList(birthDate));
        boolean isLeapYear = (boolean) manager.invoke("isLeapYear", Arrays.asList(1990));

        System.out.println("出生日期: " + birthDate);
        System.out.println("年龄: " + age);
        System.out.println("1990年是闰年吗: " + isLeapYear);

        assertTrue(age >= 34);
    }

    @Test
    public void testDateRangeChecker() {
        // 日期范围检查
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        LocalDate checkDate = LocalDate.of(2024, 6, 15);

        boolean isAfterStart = (boolean) manager.invoke("isAfter", Arrays.asList(checkDate, startDate));
        boolean isBeforeEnd = (boolean) manager.invoke("isBefore", Arrays.asList(checkDate, endDate));
        boolean inRange = isAfterStart && isBeforeEnd;

        assertTrue(inRange);
        System.out.println("日期 " + checkDate + " 在范围内: " + inRange);
    }

    @Test
    public void testWeekendChecker() {
        // 周末检查器
        for (int i = 1; i <= 7; i++) {
            LocalDate date = LocalDate.of(2024, 5, i + 5);
            boolean isWeekend = (boolean) manager.invoke("isWeekend", Arrays.asList(date));
            String dayName = (String) manager.invoke("dayOfWeek", Arrays.asList(date, "name"));
            System.out.println(dayName + " (" + date + ") 是周末: " + isWeekend);
        }
    }

    @Test
    public void testMonthsBetweenInvoice() {
        // 账单月份差计算
        LocalDate invoiceDate = LocalDate.of(2024, 1, 15);
        LocalDate dueDate = LocalDate.of(2024, 6, 15);
        long months = (long) manager.invoke("monthsBetween", Arrays.asList(invoiceDate, dueDate));
        System.out.println("账单间隔 " + months + " 个月");
        // 计算应还日期
        LocalDate newDueDate = (LocalDate) manager.invoke("addMonths", Arrays.asList(dueDate, 1));
        System.out.println("下期账单日: " + newDueDate);

        assertEquals(5, months);
    }

    @Test
    public void testDateTimeOperationChain() {
        LocalDateTime now = (LocalDateTime) manager.invoke("now", Arrays.asList());
        // 加2小时30分钟
        LocalDateTime later = (LocalDateTime) manager.invoke("addHours", Arrays.asList(now, 2));
        later = (LocalDateTime) manager.invoke("addMinutes", Arrays.asList(later, 30));
        // 提取时间部分
        int hour = (int) manager.invoke("hour", Arrays.asList(later));
        int minute = (int) manager.invoke("minute", Arrays.asList(later));
        System.out.println("当前时间: " + now);
        System.out.println("2小时30分钟后: " + later);
        System.out.println("小时: " + hour + ", 分钟: " + minute);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddDaysWithNoArgs() {
        manager.invoke("addDays", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDaysBetweenWithNoArgs() {
        manager.invoke("daysBetween", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsAfterWithNoArgs() {
        manager.invoke("isAfter", Arrays.asList());
    }
}