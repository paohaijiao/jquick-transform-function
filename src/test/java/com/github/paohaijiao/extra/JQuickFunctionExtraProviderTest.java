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
package com.github.paohaijiao.extra;
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

import java.util.*;

import static org.junit.Assert.*;

/**
 * Extra 扩展函数提供者测试类
 * 测试范围：between, cast, formatNumber, parseNumber, toArray, toList,
 *          toCurrency, toPercentage, typeOf
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionExtraProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testBetween() {
        // 3个参数 - 默认包含边界
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 1, 10)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(1, 1, 10)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(10, 1, 10)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(0, 1, 10)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(11, 1, 10)));

        // 4个参数 - 不包含边界 (inclusive = false)
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 1, 10, false)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(1, 1, 10, false)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(10, 1, 10, false)));

        // 4个参数 - 包含边界 (inclusive = true)
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 1, 10, true)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(1, 1, 10, true)));

        // 小数测试
        assertTrue((boolean) manager.invoke("between", Arrays.asList(3.14, 3.0, 3.5)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(3.14, 3.15, 3.5)));

        // 负数
        assertTrue((boolean) manager.invoke("between", Arrays.asList(-5, -10, 0)));

        // 字符串数字
        assertTrue((boolean) manager.invoke("between", Arrays.asList("5", "1", "10")));
    }


    @Test
    public void testCast() {
        // 类型匹配 - 直接返回
        String str = "hello";
        Object result = manager.invoke("cast", Arrays.asList(str, "java.lang.String"));
        assertSame(str, result);

        // 转换为 String
        result = manager.invoke("cast", Arrays.asList(123, "java.lang.String"));
        assertEquals("123", result);

        // 转换为 Integer
        result = manager.invoke("cast", Arrays.asList("456", "java.lang.Integer"));
        assertEquals(456, result);

        // 转换为 Long
        result = manager.invoke("cast", Arrays.asList("789", "java.lang.Long"));
        assertEquals(789L, result);

        // 转换为 Double
        result = manager.invoke("cast", Arrays.asList("3.14", "java.lang.Double"));
        assertEquals(3.14, (Double) result, 0.001);

        // 转换为 Boolean
        result = manager.invoke("cast", Arrays.asList("true", "java.lang.Boolean"));
        assertEquals(true, result);

        // 基本类型
        result = manager.invoke("cast", Arrays.asList(100, "int"));
        assertEquals(100, result);

        result = manager.invoke("cast", Arrays.asList(200, "long"));
        assertEquals(200L, result);

        result = manager.invoke("cast", Arrays.asList(3.14, "double"));
        assertEquals(3.14, (Double) result, 0.001);

        // null 处理
        result = manager.invoke("cast", Arrays.asList(null, "java.lang.String"));
        assertNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCastWithInvalidClass() {
        manager.invoke("cast", Arrays.asList("value", "com.invalid.Class"));
    }


    @Test
    public void testFormatNumber() {
        // 整数格式化
        String result = (String) manager.invoke("formatNumber", Arrays.asList(1234567, "#,###"));
        assertEquals("1,234,567", result);

        // 小数格式化
        result = (String) manager.invoke("formatNumber", Arrays.asList(1234.5678, "#,###.00"));
        assertEquals("1,234.57", result);

        // 货币格式
        result = (String) manager.invoke("formatNumber", Arrays.asList(1234.5, "￥#,###.00"));
        assertEquals("￥1,234.50", result);

        // 百分比格式
        result = (String) manager.invoke("formatNumber", Arrays.asList(0.1234, "#.##%"));
        assertEquals("12.34%", result);

        // 科学计数法
        result = (String) manager.invoke("formatNumber", Arrays.asList(12345.67, "0.###E0"));
        assertNotNull(result);

        // 补零
        result = (String) manager.invoke("formatNumber", Arrays.asList(5, "000"));
        assertEquals("005", result);
    }


    @Test
    public void testParseNumber() {
        // 解析带千分位的数字
        double result = (double) manager.invoke("parseNumber", Arrays.asList("1,234,567", "#,###"));
        assertEquals(1234567.0, result, 0.001);
        // 解析带小数的数字
        result = (double) manager.invoke("parseNumber", Arrays.asList("1,234.57", "#,###.00"));
        assertEquals(1234.57, result, 0.001);
        // 解析百分比
        result = (double) manager.invoke("parseNumber", Arrays.asList("12.34%", "#.##%"));
        assertEquals(0.1234, result, 0.0001);
        // 解析货币
        result = (double) manager.invoke("parseNumber", Arrays.asList("￥1,234.50", "￥#,###.00"));
        assertEquals(1234.5, result, 0.001);
    }


    @Test
    public void testToArray() {
        // 0个参数 - 空数组
        Object[] result = (Object[]) manager.invoke("toArray", Arrays.asList());
        assertEquals(0, result.length);

        // 多个参数 - 转换为数组
        result = (Object[]) manager.invoke("toArray", Arrays.asList("a", "b", "c"));
        assertEquals(3, result.length);
        assertEquals("a", result[0]);
        assertEquals("b", result[1]);
        assertEquals("c", result[2]);

        // List 作为参数 - 转换为数组
        List<String> list = Arrays.asList("x", "y", "z");
        result = (Object[]) manager.invoke("toArray", Arrays.asList(list));
        assertEquals(3, result.length);
        assertEquals("x", result[0]);
        assertEquals("y", result[1]);
        assertEquals("z", result[2]);

        // 数组作为参数 - 转换为数组（实际上是原数组）
        String[] arr = {"1", "2", "3"};
        result = (Object[]) manager.invoke("toArray", Arrays.asList((Object) arr));
        assertEquals(1, result.length);

        // 混合类型
        result = (Object[]) manager.invoke("toArray", Arrays.asList(1, "two", 3.0, true));
        assertEquals(4, result.length);
    }


    @Test
    public void testToList() {
        // 0个参数 - 空列表
        @SuppressWarnings("unchecked")
        List<Object> result = (List<Object>) manager.invoke("toList", Arrays.asList());
        assertEquals(0, result.size());

        // 多个参数 - 转换为列表
        result = (List<Object>) manager.invoke("toList", Arrays.asList("a", "b", "c"));
        assertEquals(3, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));

        // 数组作为参数
        String[] arr = {"x", "y", "z"};
        result = (List<Object>) manager.invoke("toList", Arrays.asList((Object) arr));
        assertEquals(3, result.size());
        assertEquals("x", result.get(0));

        // List 作为参数 - 返回新列表
        List<String> original = Arrays.asList("1", "2", "3");
        result = (List<Object>) manager.invoke("toList", Arrays.asList(original));
        assertEquals(3, result.size());
        assertNotSame(original, result); // 应该是新列表

        // 混合类型
        result = (List<Object>) manager.invoke("toList", Arrays.asList(1, "two", 3.0, true));
        assertEquals(4, result.size());
    }


    @Test
    public void testToCurrency() {
        // 1个参数 - 默认中国货币格式
        String result = (String) manager.invoke("toCurrency", Arrays.asList(1234.56));
        assertTrue(result.contains("1,234.56") || result.contains("1234.56"));

        // 2个参数 - US 美元格式
        result = (String) manager.invoke("toCurrency", Arrays.asList(1234.56, "us"));
        assertTrue(result.contains("$"));

        // 2个参数 - CN 人民币格式
        result = (String) manager.invoke("toCurrency", Arrays.asList(1234.56, "cn"));
        assertTrue(result.contains("¥") || result.contains("CNY"));

        // 2个参数 - UK 英镑格式
        result = (String) manager.invoke("toCurrency", Arrays.asList(1234.56, "uk"));
        assertTrue(result.contains("£"));

        // 负数
        result = (String) manager.invoke("toCurrency", Arrays.asList(-1234.56, "us"));
        assertNotNull(result);
        assertTrue(result.contains("-") || result.contains("("));

        // 零
        result = (String) manager.invoke("toCurrency", Arrays.asList(0, "us"));
        assertNotNull(result);
    }


    @Test
    public void testToPercentage() {
        // 1个参数 - 默认2位小数
        String result = (String) manager.invoke("toPercentage", Arrays.asList(0.1234));
        assertEquals("12.34%", result);

        // 2个参数 - 指定小数位数
        result = (String) manager.invoke("toPercentage", Arrays.asList(0.1234, 0));
        assertEquals("12%", result);

        result = (String) manager.invoke("toPercentage", Arrays.asList(0.1234, 1));
        assertEquals("12.3%", result);

        result = (String) manager.invoke("toPercentage", Arrays.asList(0.1234, 3));
        assertEquals("12.340%", result);

        // 整数百分比
        result = (String) manager.invoke("toPercentage", Arrays.asList(1.0, 0));
        assertEquals("100%", result);

        // 小数转换
        result = (String) manager.invoke("toPercentage", Arrays.asList(0.5, 0));
        assertEquals("50%", result);

        // 大于1的数
        result = (String) manager.invoke("toPercentage", Arrays.asList(1.5, 0));
        assertEquals("150%", result);

        // 负数
        result = (String) manager.invoke("toPercentage", Arrays.asList(-0.1234, 2));
        assertEquals("-12.34%", result);
    }


    @Test
    public void testTypeOf() {
        // String
        String type = (String) manager.invoke("typeOf", Arrays.asList("hello"));
        assertEquals("string", type);

        // Number
        type = (String) manager.invoke("typeOf", Arrays.asList(123));
        assertEquals("number", type);

        type = (String) manager.invoke("typeOf", Arrays.asList(3.14));
        assertEquals("number", type);

        type = (String) manager.invoke("typeOf", Arrays.asList(100L));
        assertEquals("number", type);

        // Boolean
        type = (String) manager.invoke("typeOf", Arrays.asList(true));
        assertEquals("boolean", type);

        // List
        type = (String) manager.invoke("typeOf", Arrays.asList(Arrays.asList("a", "b")));
        assertEquals("array", type);

        // Map
        type = (String) manager.invoke("typeOf", Arrays.asList(new HashMap<String, String>()));
        assertEquals("object", type);

        // Array
        type = (String) manager.invoke("typeOf", Arrays.asList((Object) new String[]{"a", "b"}));
        assertEquals("array", type);

        // null
        type = (String) manager.invoke("typeOf", Arrays.asList((Object) null));
        assertEquals("null", type);

        // 其他类型
        type = (String) manager.invoke("typeOf", Arrays.asList(new Date()));
        assertEquals("date", type);

        type = (String) manager.invoke("typeOf", Arrays.asList(new ArrayList<>()));
        assertEquals("array", type);
    }

    // ==================== 参数校验测试 ====================

    @Test(expected = IllegalArgumentException.class)
    public void testBetweenWithTooFewArgs() {
        manager.invoke("between", Arrays.asList(5, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCastWithTooFewArgs() {
        manager.invoke("cast", Arrays.asList("value"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatNumberWithTooFewArgs() {
        manager.invoke("formatNumber", Arrays.asList(123));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseNumberWithTooFewArgs() {
        manager.invoke("parseNumber", Arrays.asList("123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToCurrencyWithNoArgs() {
        manager.invoke("toCurrency", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testToPercentageWithNoArgs() {
        manager.invoke("toPercentage", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTypeOfWithNoArgs() {
        manager.invoke("typeOf", Arrays.asList());
    }


    @Test
    public void testBetweenEdgeCases() {
        // 最大值最小值相等
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 5, 5)));
        assertTrue((boolean) manager.invoke("between", Arrays.asList(5, 5, 5, true)));
        assertFalse((boolean) manager.invoke("between", Arrays.asList(5, 5, 5, false)));
        // 大数值
        assertTrue((boolean) manager.invoke("between", Arrays.asList(1e10, 1e9, 1e11)));
    }

    @Test
    public void testFormatNumberEdgeCases() {
        // 零
        String result = (String) manager.invoke("formatNumber", Arrays.asList(0, "#,###.00"));
        assertEquals(".00", result); // DecimalFormat 行为

        // 负数
        result = (String) manager.invoke("formatNumber", Arrays.asList(-1234.56, "#,###.00"));
        assertTrue(result.startsWith("-"));
    }

    @Test
    public void testToPercentageWithZero() {
        String result = (String) manager.invoke("toPercentage", Arrays.asList(0, 2));
        assertEquals("0.00%", result);
    }

    @Test
    public void testTypeOfEdgeCases() {
        // 空字符串
        String type = (String) manager.invoke("typeOf", Arrays.asList(""));
        assertEquals("string", type);

        // 空数组
        type = (String) manager.invoke("typeOf", Arrays.asList((Object) new String[0]));
        assertEquals("array", type);

        // 空列表
        type = (String) manager.invoke("typeOf", Arrays.asList(new ArrayList<>()));
        assertEquals("array", type);
    }


    @Test
    public void testNumberFormattingChain() {
        // 数字格式化链：原值 -> 格式化 -> 解析 -> 百分比
        double original = 0.123456;
        // 格式化为百分比字符串
        String percentStr = (String) manager.invoke("toPercentage", Arrays.asList(original, 2));
        assertEquals("12.35%", percentStr);

        // 解析回数字
        double parsed = (double) manager.invoke("parseNumber", Arrays.asList(percentStr, "#.##%"));
        assertEquals(0.1235, parsed, 0.0001);

        System.out.println("原始值: " + original);
        System.out.println("百分比格式: " + percentStr);
        System.out.println("解析后: " + parsed);
    }

    @Test
    public void testCurrencyConversion() {
        // 货币格式化
        double amount = 12345.67;

        String usd = (String) manager.invoke("toCurrency", Arrays.asList(amount, "us"));
        String cny = (String) manager.invoke("toCurrency", Arrays.asList(amount, "cn"));
        String gbp = (String) manager.invoke("toCurrency", Arrays.asList(amount, "uk"));

        System.out.println("USD: " + usd);
        System.out.println("CNY: " + cny);
        System.out.println("GBP: " + gbp);

        assertNotNull(usd);
        assertNotNull(cny);
        assertNotNull(gbp);
    }

    @Test
    public void testTypeDetectionAndCast() {
        // 类型检测和转换
        Object value = "123";

        String type = (String) manager.invoke("typeOf", Arrays.asList(value));
        assertEquals("string", type);

        // 转换为数字
        int intValue = (int) manager.invoke("cast", Arrays.asList(value, "int"));
        assertEquals(123, intValue);

        // 再次检测类型
        type = (String) manager.invoke("typeOf", Arrays.asList(intValue));
        assertEquals("number", type);

        System.out.println("原始值: " + value + " (类型: string)");
        System.out.println("转换后: " + intValue + " (类型: number)");
    }

    @Test
    public void testRangeCheckScenario() {
        // 范围检查场景
        double price = 99.99;
        double min = 0;
        double max = 100;

        boolean inRange = (boolean) manager.invoke("between", Arrays.asList(price, min, max));
        assertTrue(inRange);

        boolean inRangeExclusive = (boolean) manager.invoke("between", Arrays.asList(price, min, max, false));
        assertTrue(inRangeExclusive);

        // 边界测试
        boolean atMax = (boolean) manager.invoke("between", Arrays.asList(100.0, 0, 100));
        assertTrue(atMax);

        boolean atMaxExclusive = (boolean) manager.invoke("between", Arrays.asList(100.0, 0, 100, false));
        assertFalse(atMaxExclusive);

        System.out.println("价格 " + price + " 在范围内: " + inRange);
    }

    @Test
    public void testCollectionConversion() {
        // 集合转换场景
        String[] colors = {"red", "green", "blue"};

        // 数组转列表
        @SuppressWarnings("unchecked")
        List<Object> colorList = (List<Object>) manager.invoke("toList", Arrays.asList((Object) colors));
        assertEquals(3, colorList.size());

        // 列表转数组
        Object[] colorArray = (Object[]) manager.invoke("toArray", Arrays.asList(colorList));
        assertEquals(3, colorArray.length);

        // 验证一致性
        for (int i = 0; i < 3; i++) {
            assertEquals(colors[i], colorList.get(i));
            assertEquals(colors[i], colorArray[i]);
        }

        System.out.println("原始数组: " + Arrays.toString(colors));
        System.out.println("转换后的列表: " + colorList);
        System.out.println("转换回的数组: " + Arrays.toString(colorArray));
    }

    @Test
    public void testDiscountCalculation() {
        // 折扣计算场景
        double originalPrice = 299.00;
        double discount = 0.25; // 25% off
        double finalPrice = originalPrice * (1 - discount);
        // 格式化输出
        String formattedOriginal = (String) manager.invoke("toCurrency", Arrays.asList(originalPrice, "cn"));
        String formattedFinal = (String) manager.invoke("toCurrency", Arrays.asList(finalPrice, "cn"));
        String discountPercent = (String) manager.invoke("toPercentage", Arrays.asList(discount, 0));
        System.out.println("原价: " + formattedOriginal);
        System.out.println("折扣: " + discountPercent);
        System.out.println("折后价: " + formattedFinal);
        assertNotNull(formattedOriginal);
        assertNotNull(formattedFinal);
        assertEquals("25%", discountPercent);
    }

    @Test
    public void testScoreGradeMapping() {
        // 成绩等级映射场景
        double score = 85.5;
        String grade;
        if ((boolean) manager.invoke("between", Arrays.asList(score, 90, 100))) {
            grade = "A";
        } else if ((boolean) manager.invoke("between", Arrays.asList(score, 80, 89.99, false))) {
            grade = "B";
        } else if ((boolean) manager.invoke("between", Arrays.asList(score, 70, 79.99, false))) {
            grade = "C";
        } else if ((boolean) manager.invoke("between", Arrays.asList(score, 60, 69.99, false))) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("得分: " + score + " -> 等级: " + grade);
        assertEquals("B", grade);
    }
}