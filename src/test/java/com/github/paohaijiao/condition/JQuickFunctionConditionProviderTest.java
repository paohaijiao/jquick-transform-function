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
package com.github.paohaijiao.condition;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * packageName com.github.paohaijiao.collection
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionConditionProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testBetween() {
        // 3个参数（默认包含边界）
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 1, 10)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(1, 1, 10)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(10, 1, 10)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(0, 1, 10)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(11, 1, 10)));

        // 4个参数（不包含边界）
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 1, 10, false)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(1, 1, 10, false)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(10, 1, 10, false)));

        // 小数测试
        assertTrue((boolean) manager.invoke("between", Arrays.asList(3.14, 3.0, 3.5)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(3.14, 3.15, 3.5)));

        // 字符串数字
        assertTrue((boolean) manager.invoke("between", Arrays.asList("5", "1", "10")));

        // 负数
        assertTrue((boolean) manager.invoke("between", Arrays.asList(-5, -10, 0)));
    }

    @Test
    public void testCaseWhen() {
        // 奇数组参数（有默认值）
        String result = (String) manager.invoke("caseWhen",
                Arrays.asList(true, "第一个", false, "第二个", "默认值"));
        assertEquals("第一个", result);

        result = (String) manager.invoke("caseWhen",
                Arrays.asList(false, "第一个", true, "第二个", "默认值"));
        assertEquals("第二个", result);

        result = (String) manager.invoke("caseWhen",
                Arrays.asList(false, "第一个", false, "第二个", "默认值"));
        assertEquals("默认值", result);

        // 偶数组参数（无默认值，返回 null）
        Object nullResult = manager.invoke("caseWhen",
                Arrays.asList(false, "第一个", false, "第二个"));
        assertNull(nullResult);

        // 数值条件
        int intResult = (int) manager.invoke("caseWhen",
                Arrays.asList(5 > 3, 100, 5 > 10, 200, 300));
        assertEquals(100, intResult);

        // 条件表达式
        String scoreResult = (String) manager.invoke("caseWhen",
                Arrays.asList(90 >= 90, "优秀", 80 >= 80, "良好", 60 >= 60, "及格", "不及格"));
        assertEquals("优秀", scoreResult);
    }

    @Test
    public void testCoalesce() {
        // 返回第一个非空值
        String result = (String) manager.invoke("coalesce",
                Arrays.asList(null, null, "first", "second"));
        assertEquals("first", result);

        result = (String) manager.invoke("coalesce",
                Arrays.asList(null, "first", "second"));
        assertEquals("first", result);

        // 全部为 null
        Object nullResult = manager.invoke("coalesce",
                Arrays.asList(null, null, null));
        assertNull(nullResult);

        // 不同类型混合
        Object mixed = manager.invoke("coalesce",
                Arrays.asList(null, 123, "string"));
        assertEquals(123, mixed);

        // 单个参数
        String single = (String) manager.invoke("coalesce", Arrays.asList("single"));
        assertEquals("single", single);
    }

    @Test
    public void testDefaultIfNull() {
        // 非空值
        String result = (String) manager.invoke("defaultIfNull", Arrays.asList("value", "default"));
        assertEquals("value", result);

        // null 值
        result = (String) manager.invoke("defaultIfNull", Arrays.asList(null, "default"));
        assertEquals("default", result);

        // 数字类型
        int intResult = (int) manager.invoke("defaultIfNull", Arrays.asList(100, 0));
        assertEquals(100, intResult);

        intResult = (int) manager.invoke("defaultIfNull", Arrays.asList(null, 0));
        assertEquals(0, intResult);
    }

    @Test
    public void testEq() {
        // 2个参数 - 相等
        assertTrue((boolean) manager.invoke("eq", Arrays.asList("hello", "hello")));
        assertFalse((boolean) manager.invoke("eq", Arrays.asList("hello", "world")));

        // 数字相等
        assertTrue((boolean) manager.invoke("eq", Arrays.asList(5, 5)));
        assertFalse((boolean) manager.invoke("eq", Arrays.asList(5, 6)));

        // null 处理
        assertTrue((boolean) manager.invoke("eq", Arrays.asList(null, null)));
        assertFalse((boolean) manager.invoke("eq", Arrays.asList("hello", null)));
        assertFalse((boolean) manager.invoke("eq", Arrays.asList(null, "hello")));

        // 3个参数 - 忽略大小写
        assertTrue((boolean) manager.invoke("eq", Arrays.asList("Hello", "HELLO", true)));
        assertFalse((boolean) manager.invoke("eq", Arrays.asList("Hello", "HELLO", false)));

        // 不同类型
        assertFalse((boolean) manager.invoke("eq", Arrays.asList("5", 5)));
    }

    @Test
    public void testGt() {
        // 大于
        assertTrue((boolean) manager.invoke("gt", Arrays.asList(10, 5)));
        assertFalse((boolean) manager.invoke("gt", Arrays.asList(5, 10)));
        assertFalse((boolean) manager.invoke("gt", Arrays.asList(5, 5)));

        // 小数
        assertTrue((boolean) manager.invoke("gt", Arrays.asList(3.14, 3.13)));
        assertFalse((boolean) manager.invoke("gt", Arrays.asList(3.14, 3.15)));

        // 字符串数字
        assertTrue((boolean) manager.invoke("gt", Arrays.asList("10", "5")));
    }

    @Test
    public void testGte() {
        // 大于等于
        assertTrue((boolean) manager.invoke("gte", Arrays.asList(10, 5)));
        assertFalse((boolean) manager.invoke("gte", Arrays.asList(5, 10)));
        assertTrue((boolean) manager.invoke("gte", Arrays.asList(5, 5)));

        // 小数
        assertTrue((boolean) manager.invoke("gte", Arrays.asList(3.14, 3.14)));
    }

    @Test
    public void testLt() {
        // 小于
        assertTrue((boolean) manager.invoke("lt", Arrays.asList(5, 10)));
        assertFalse((boolean) manager.invoke("lt", Arrays.asList(10, 5)));
        assertFalse((boolean) manager.invoke("lt", Arrays.asList(5, 5)));

        // 小数
        assertTrue((boolean) manager.invoke("lt", Arrays.asList(3.13, 3.14)));
    }

    @Test
    public void testLte() {
        // 小于等于
        assertTrue((boolean) manager.invoke("lte", Arrays.asList(5, 10)));
        assertFalse((boolean) manager.invoke("lte", Arrays.asList(10, 5)));
        assertTrue((boolean) manager.invoke("lte", Arrays.asList(5, 5)));
    }

    @Test
    public void testNe() {
        // 2个参数 - 不等
        assertTrue((boolean) manager.invoke("ne", Arrays.asList("hello", "world")));
        assertFalse((boolean) manager.invoke("ne", Arrays.asList("hello", "hello")));

        // null 处理
        assertFalse((boolean) manager.invoke("ne", Arrays.asList(null, null)));
        assertTrue((boolean) manager.invoke("ne", Arrays.asList("hello", null)));
        assertTrue((boolean) manager.invoke("ne", Arrays.asList(null, "hello")));

        // 3个参数 - 忽略大小写
        assertFalse((boolean) manager.invoke("ne", Arrays.asList("Hello", "HELLO", true)));
        assertTrue((boolean) manager.invoke("ne", Arrays.asList("Hello", "HELLO", false)));
    }

    @Test
    public void testNvl() {
        // 非空值
        String result = (String) manager.invoke("nvl", Arrays.asList("value", "default"));
        assertEquals("value", result);

        // null 值
        result = (String) manager.invoke("nvl", Arrays.asList(null, "default"));
        assertEquals("default", result);

        // 数字类型
        int intResult = (int) manager.invoke("nvl", Arrays.asList(100, 0));
        assertEquals(100, intResult);
    }

    @Test
    public void testIf() {
        // 条件为 true
        String result = (String) manager.invoke("if", Arrays.asList(true, "真值", "假值"));
        assertEquals("真值", result);

        // 条件为 false
        result = (String) manager.invoke("if", Arrays.asList(false, "真值", "假值"));
        assertEquals("假值", result);

        // 数字条件
        int intResult = (int) manager.invoke("if", Arrays.asList(5 > 3, 100, 0));
        assertEquals(100, intResult);

        intResult = (int) manager.invoke("if", Arrays.asList(5 < 3, 100, 0));
        assertEquals(0, intResult);

        // 字符串条件
        String strResult = (String) manager.invoke("if", Arrays.asList("true", "是", "否"));
        assertEquals("是", strResult);
    }

    @Test
    public void testIfElse() {
        // 第一个条件命中
        String result = (String) manager.invoke("ifElse",
                Arrays.asList(true, "第一个", false, "第二个", false, "第三个", "默认值"));
        assertEquals("第一个", result);

        // 第二个条件命中
        result = (String) manager.invoke("ifElse",
                Arrays.asList(false, "第一个", true, "第二个", false, "第三个", "默认值"));
        assertEquals("第二个", result);

        // 第三个条件命中
        result = (String) manager.invoke("ifElse",
                Arrays.asList(false, "第一个", false, "第二个", true, "第三个", "默认值"));
        assertEquals("第三个", result);

        // 无条件命中，返回默认值
        result = (String) manager.invoke("ifElse",
                Arrays.asList(false, "第一个", false, "第二个", false, "第三个", "默认值"));
        assertEquals("默认值", result);

        // 数值条件
        int intResult = (int) manager.invoke("ifElse",
                Arrays.asList(90 >= 90, 90, 80 >= 80, 80, 70 >= 70, 70, 0));
        assertEquals(90, intResult);

        // 分数等级判断
        int score = 85;
        String grade = (String) manager.invoke("ifElse",
                Arrays.asList(score >= 90, "优秀", score >= 80, "良好", score >= 60, "及格", "不及格"));
        assertEquals("良好", grade);
    }

    @Test
    public void testSwitch() {
        // 匹配第一个 case
        String result = (String) manager.invoke("switch",
                Arrays.asList("A", "A", "第一名", "B", "第二名", "C", "第三名", "未知"));
        assertEquals("第一名", result);

        // 匹配第二个 case
        result = (String) manager.invoke("switch",
                Arrays.asList("B", "A", "第一名", "B", "第二名", "C", "第三名", "未知"));
        assertEquals("第二名", result);

        // 匹配第三个 case
        result = (String) manager.invoke("switch",
                Arrays.asList("C", "A", "第一名", "B", "第二名", "C", "第三名", "未知"));
        assertEquals("第三名", result);

        // 无匹配，返回默认值
        result = (String) manager.invoke("switch",
                Arrays.asList("D", "A", "第一名", "B", "第二名", "C", "第三名", "未知"));
        assertEquals("未知", result);

        // 数字匹配
        String numberResult = (String) manager.invoke("switch",
                Arrays.asList(1, 1, "一", 2, "二", 3, "三", "其他"));
        assertEquals("一", numberResult);

        // null 匹配
        String nullResult = (String) manager.invoke("switch",
                Arrays.asList(null, "A", "A值", null, "空值", "默认值"));
        assertEquals("空值", nullResult);

        // 类型不同不匹配
        String typeResult = (String) manager.invoke("switch",
                Arrays.asList("1", 1, "数字1", "1", "字符串1", "默认"));
        assertEquals("字符串1", typeResult);
    }

}
