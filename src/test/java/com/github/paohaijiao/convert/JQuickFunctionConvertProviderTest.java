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
package com.github.paohaijiao.convert;

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
 * Convert 类型转换函数提供者测试类
 * 测试范围：toBoolean, toDate, toDateTime, toShort
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionConvertProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }
    @Test
    public void testToBoolean() {
        // 1个参数 - Boolean 类型
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList(true)));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList(false)));

        // 1个参数 - Number 类型（非0为true）
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList(1)));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList(100)));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList(-1)));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList(0)));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList(3.14)));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList(0.0)));

        // 1个参数 - String 类型（true/yes/1/on 为 true）
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("true")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("True")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("TRUE")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("yes")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("YES")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("1")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("on")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("ON")));

        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("false")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("no")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("0")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("off")));

        // 1个参数 - 无效值应抛异常
        try {
            manager.invoke("toBoolean", Arrays.asList("invalid"));
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("无法转换为布尔值"));
        }

        // 2个参数 - null 值返回默认值
        boolean result = (boolean) manager.invoke("toBoolean", Arrays.asList(null, true));
        assertTrue(result);

        result = (boolean) manager.invoke("toBoolean", Arrays.asList(null, false));
        assertFalse(result);

        // 2个参数 - 无效值返回默认值
        result = (boolean) manager.invoke("toBoolean", Arrays.asList("invalid", true));
        assertTrue(result);

        result = (boolean) manager.invoke("toBoolean", Arrays.asList("invalid", false));
        assertFalse(result);

        // 2个参数 - 有效值忽略默认值
        result = (boolean) manager.invoke("toBoolean", Arrays.asList("true", false));
        assertTrue(result);

        result = (boolean) manager.invoke("toBoolean", Arrays.asList("false", true));
        assertFalse(result);
    }
    @Test
    public void testToDate() {
        // 1个参数 - LocalDate 类型
        LocalDate sourceDate = LocalDate.of(2024, 5, 10);
        LocalDate result = (LocalDate) manager.invoke("toDate", Arrays.asList(sourceDate));
        assertEquals(sourceDate, result);

        // 1个参数 - LocalDateTime 类型（转换为 LocalDate）
        LocalDateTime sourceDateTime = LocalDateTime.of(2024, 5, 10, 15, 30, 0);
        result = (LocalDate) manager.invoke("toDate", Arrays.asList(sourceDateTime));
        assertEquals(LocalDate.of(2024, 5, 10), result);

        // 1个参数 - java.util.Date 类型
        Date utilDate = new Date(2024 - 1900, 4, 10); // 注意：月份从0开始
        result = (LocalDate) manager.invoke("toDate", Arrays.asList(utilDate));
        assertNotNull(result);

        // 1个参数 - ISO 格式字符串
        result = (LocalDate) manager.invoke("toDate", Arrays.asList("2024-05-10"));
        assertEquals(LocalDate.of(2024, 5, 10), result);


        // 2个参数 - 指定日期格式
        result = (LocalDate) manager.invoke("toDate", Arrays.asList("10/05/2024", "dd/MM/yyyy"));
        assertEquals(LocalDate.of(2024, 5, 10), result);

        result = (LocalDate) manager.invoke("toDate", Arrays.asList("2024年05月10日", "yyyy年MM月dd日"));
        assertEquals(LocalDate.of(2024, 5, 10), result);

        result = (LocalDate) manager.invoke("toDate", Arrays.asList("10-05-2024", "dd-MM-yyyy"));
        assertEquals(LocalDate.of(2024, 5, 10), result);

        // null 处理
        result = (LocalDate) manager.invoke("toDate", Arrays.asList((Object) null));
        assertNull(result);
    }

    @Test
    public void testToDateWithInvalidFormat() {
        // 无效格式，会尝试使用默认格式
        LocalDate result = (LocalDate) manager.invoke("toDate", Arrays.asList("2024/05/10"));
        assertNotNull(result);
    }


    @Test
    public void testToDateTime() {
        // 1个参数 - LocalDateTime 类型
        LocalDateTime sourceDateTime = LocalDateTime.of(2024, 5, 10, 15, 30, 0);
        LocalDateTime result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList(sourceDateTime));
        assertEquals(sourceDateTime, result);

        // 1个参数 - LocalDate 类型（转换为当天的 00:00:00）
        LocalDate sourceDate = LocalDate.of(2024, 5, 10);
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList(sourceDate));
        assertEquals(LocalDateTime.of(2024, 5, 10, 0, 0, 0), result);

        // 1个参数 - java.util.Date 类型
        Date utilDate = new Date(2024 - 1900, 4, 10, 15, 30, 0);
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList(utilDate));
        assertNotNull(result);

        // 1个参数 - ISO 格式字符串（yyyy-MM-ddTHH:mm:ss）
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("2024-05-10T15:30:00"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 0), result);

        // 1个参数 - 标准格式字符串
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("2024-05-10 15:30:00"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 0), result);

        // 2个参数 - 指定日期时间格式
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("10/05/2024 15:30:00", "dd/MM/yyyy HH:mm:ss"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 0), result);

        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("2024年05月10日 15时30分00秒", "yyyy年MM月dd日 HH时mm分ss秒"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 0), result);

        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("10-05-2024 15:30", "dd-MM-yyyy HH:mm"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 15, 30, 0), result);

        // null 处理
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList((Object) null));
        assertNull(result);
    }



    @Test
    public void testToShort() {
        // 1个参数 - Number 类型
        short result = (short) manager.invoke("toShort", Arrays.asList(123));
        assertEquals(123, result);

        result = (short) manager.invoke("toShort", Arrays.asList(32767)); // Short.MAX_VALUE
        assertEquals(32767, result);

        result = (short) manager.invoke("toShort", Arrays.asList(-32768)); // Short.MIN_VALUE
        assertEquals(-32768, result);

        // 浮点数转 short（截断小数）
        result = (short) manager.invoke("toShort", Arrays.asList(123.45));
        assertEquals(123, result);

        result = (short) manager.invoke("toShort", Arrays.asList(123.99));
        assertEquals(123, result);

        // 1个参数 - String 类型
        result = (short) manager.invoke("toShort", Arrays.asList("456"));
        assertEquals(456, result);

        result = (short) manager.invoke("toShort", Arrays.asList("-789"));
        assertEquals(-789, result);

        // 1个参数 - 带空格的字符串
        result = (short) manager.invoke("toShort", Arrays.asList("  123  "));
        assertEquals(123, result);



        // 2个参数 - null 值返回默认值
        result = (short) manager.invoke("toShort", Arrays.asList(null, 999));
        assertEquals(999, result);

        result = (short) manager.invoke("toShort", Arrays.asList(null, 0));
        assertEquals(0, result);

        // 2个参数 - 无效值返回默认值
        result = (short) manager.invoke("toShort", Arrays.asList("invalid", 888));
        assertEquals(888, result);

        result = (short) manager.invoke("toShort", Arrays.asList("", 777));
        assertEquals(777, result);

        // 2个参数 - 有效值忽略默认值
        result = (short) manager.invoke("toShort", Arrays.asList("123", 999));
        assertEquals(123, result);

        result = (short) manager.invoke("toShort", Arrays.asList(456, 999));
        assertEquals(456, result);
    }

    @Test
    public void testToShortWithLargeNumbers() {
        // 超出 short 范围的值，使用默认值
        short result = (short) manager.invoke("toShort", Arrays.asList(100000, 0));
        assertEquals(0, result);

        result = (short) manager.invoke("toShort", Arrays.asList("100000", 0));
        assertEquals(0, result);
    }

    @Test
    public void testToBooleanEdgeCases() {
        // 各种 true 值的变体
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("True")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("TRUE")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("tRuE")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("Yes")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("YES")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("yEs")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("On")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("ON")));
        assertTrue((boolean) manager.invoke("toBoolean", Arrays.asList("oN")));

        // 各种 false 值的变体
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("False")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("FALSE")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("No")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("NO")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("Off")));
        assertFalse((boolean) manager.invoke("toBoolean", Arrays.asList("OFF")));
    }

    @Test
    public void testToDateEdgeCases() {
        // 闰年
        LocalDate result = (LocalDate) manager.invoke("toDate", Arrays.asList("2020-02-29"));
        assertEquals(LocalDate.of(2020, 2, 29), result);

        // 月底
        result = (LocalDate) manager.invoke("toDate", Arrays.asList("2024-01-31"));
        assertEquals(LocalDate.of(2024, 1, 31), result);
    }

    @Test
    public void testToDateTimeEdgeCases() {
        // 闰年
        LocalDateTime result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("2020-02-29T23:59:59"));
        assertEquals(LocalDateTime.of(2020, 2, 29, 23, 59, 59), result);

        // 午夜
        result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList("2024-05-10T00:00:00"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 0, 0, 0), result);
    }

    @Test
    public void testToShortEdgeCases() {
        // 边界值
        short result = (short) manager.invoke("toShort", Arrays.asList(32767));
        assertEquals(32767, result);

        result = (short) manager.invoke("toShort", Arrays.asList(-32768));
        assertEquals(-32768, result);

        // 字符串边界值
        result = (short) manager.invoke("toShort", Arrays.asList("32767"));
        assertEquals(32767, result);

        result = (short) manager.invoke("toShort", Arrays.asList("-32768"));
        assertEquals(-32768, result);
    }
    @Test
    public void testComprehensiveConvertScenario() {
        // 字符串转布尔
        boolean isActive = (boolean) manager.invoke("toBoolean", Arrays.asList("yes"));
        assertTrue(isActive);

        // 字符串转日期
        LocalDate birthDate = (LocalDate) manager.invoke("toDate", Arrays.asList("1990-03-07"));
        assertEquals(LocalDate.of(1990, 3, 7), birthDate);

        // 字符串转日期时间
        LocalDateTime registerTime = (LocalDateTime) manager.invoke("toDateTime",
                Arrays.asList("2024-05-10 14:30:00"));
        assertEquals(LocalDateTime.of(2024, 5, 10, 14, 30, 0), registerTime);

        // 数字转短整数
        short age = (short) manager.invoke("toShort", Arrays.asList(34));
        assertEquals(34, age);

        System.out.println("转换结果: isActive=" + isActive +
                ", birthDate=" + birthDate +
                ", registerTime=" + registerTime +
                ", age=" + age);
    }

    @Test
    public void testNullSafeConvertScenario() {
        // 安全的布尔转换
        boolean flag = (boolean) manager.invoke("toBoolean", Arrays.asList(null, false));
        assertFalse(flag);

        // 安全的短整数转换
        short value = (short) manager.invoke("toShort", Arrays.asList(null, 100));
        assertEquals(100, value);

        // 安全的日期转换
        LocalDate date = (LocalDate) manager.invoke("toDate", Arrays.asList((Object) null));
        assertNull(date);

        System.out.println("空值安全转换测试通过");
    }

    @Test
    public void testDateFormatVariants() {
        // 各种日期格式
        String[] patterns = {
                "yyyy-MM-dd", "2024-05-10",
                "dd/MM/yyyy", "10/05/2024",
                "yyyy/MM/dd", "2024/05/10",
                "dd-MM-yyyy", "10-05-2024",
                "yyyy年MM月dd日", "2024年05月10日"
        };

        for (int i = 0; i < patterns.length; i += 2) {
            String pattern = patterns[i];
            String dateStr = patterns[i + 1];
            LocalDate result = (LocalDate) manager.invoke("toDate", Arrays.asList(dateStr, pattern));
            assertEquals("格式 " + pattern + " 解析失败", LocalDate.of(2024, 5, 10), result);
            System.out.println("解析成功: " + pattern + " -> " + dateStr + " = " + result);
        }
    }

    @Test
    public void testDateTimeFormatVariants() {
        // 各种日期时间格式
        String[][] testCases = {
                {"yyyy-MM-dd HH:mm:ss", "2024-05-10 15:30:45"},
                {"dd/MM/yyyy HH:mm:ss", "10/05/2024 15:30:45"},
                {"yyyy-MM-dd'T'HH:mm:ss", "2024-05-10T15:30:45"},
                {"yyyy/MM/dd HH:mm", "2024/05/10 15:30"},
                {"dd-MM-yyyy HH:mm:ss", "10-05-2024 15:30:45"}
        };

        for (String[] testCase : testCases) {
            String pattern = testCase[0];
            String dateTimeStr = testCase[1];
            LocalDateTime result = (LocalDateTime) manager.invoke("toDateTime", Arrays.asList(dateTimeStr, pattern));
            assertEquals("格式 " + pattern + " 解析失败",
                    LocalDateTime.of(2024, 5, 10, 15, 30, 45), result);
            System.out.println("解析成功: " + pattern + " -> " + dateTimeStr + " = " + result);
        }
    }

    @Test
    public void testTypeConversionChain() {
        // 类型转换链：String -> Boolean -> Short -> Date

        // String to Boolean
        boolean boolValue = (boolean) manager.invoke("toBoolean", Arrays.asList("1"));
        assertTrue(boolValue);

        // Boolean to Short (布尔值 true 转为 1, false 转为 0)
        short shortValue = (short) manager.invoke("toShort", Arrays.asList(boolValue));
        assertEquals(1, shortValue);

        // Short to String 隐式转换
        String strValue = String.valueOf(shortValue);

        // String to Date
        LocalDate dateValue = (LocalDate) manager.invoke("toDate", Arrays.asList(strValue + "-01-01"));

        System.out.println("类型转换链结果: bool=" + boolValue +
                ", short=" + shortValue +
                ", date=" + dateValue);
    }
}