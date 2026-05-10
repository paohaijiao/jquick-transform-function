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
package com.github.paohaijiao.math;


import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Math 数学函数提供者测试类
 * 测试范围：基本运算、三角函数、指数对数、取整、统计、进制转换等
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionMathProviderTest {

    private JQuickMethodInvocationManager manager;

    private static final double DELTA = 0.0001;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }


    @Test
    public void testAbs() {
        double result = (double) manager.invoke("abs", Arrays.asList(-5.0));
        assertEquals(5.0, result, DELTA);

        result = (double) manager.invoke("abs", Arrays.asList(5.0));
        assertEquals(5.0, result, DELTA);

        result = (double) manager.invoke("abs", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testAdd() {
        double result = (double) manager.invoke("add", Arrays.asList(1, 2, 3, 4));
        assertEquals(10.0, result, DELTA);

        result = (double) manager.invoke("add", Arrays.asList(5.5, 2.5));
        assertEquals(8.0, result, DELTA);

        result = (double) manager.invoke("add", Arrays.asList(-1, 1));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testSubtract() {
        double result = (double) manager.invoke("subtract", Arrays.asList(10, 3));
        assertEquals(7.0, result, DELTA);

        result = (double) manager.invoke("subtract", Arrays.asList(5.5, 2.2));
        assertEquals(3.3, result, DELTA);

        result = (double) manager.invoke("subtract", Arrays.asList(10, 3, 2));
        assertEquals(5.0, result, DELTA);
    }

    @Test
    public void testMultiply() {
        double result = (double) manager.invoke("multiply", Arrays.asList(2, 3, 4));
        assertEquals(24.0, result, DELTA);

        result = (double) manager.invoke("multiply", Arrays.asList(5.5, 2));
        assertEquals(11.0, result, DELTA);

        result = (double) manager.invoke("multiply", Arrays.asList());
        assertEquals(1.0, result, DELTA);
    }
    @Test
    public void testStdDev() {
        // 测试基本数据集
        double result = (double) manager.invoke("stdDev", Arrays.asList(1, 2, 3, 4, 5));
        // 方差 = ((1-3)²+(2-3)²+(3-3)²+(4-3)²+(5-3)²)/5 = (4+1+0+1+4)/5 = 10/5 = 2
        // 标准差 = √2 ≈ 1.4142135623730951
        assertEquals(Math.sqrt(2.0), result, DELTA);

        // 测试所有值相等
        result = (double) manager.invoke("stdDev", Arrays.asList(10, 10, 10, 10));
        assertEquals(0.0, result, DELTA);

        // 测试两个不同的值
        result = (double) manager.invoke("stdDev", Arrays.asList(1, 3));
        // 平均值 = 2, 方差 = ((1-2)²+(3-2)²)/2 = (1+1)/2 = 1, 标准差 = 1
        assertEquals(1.0, result, DELTA);

        // 测试负数
        result = (double) manager.invoke("stdDev", Arrays.asList(-2, -1, 0, 1, 2));
        // 平均值 = 0, 方差 = (4+1+0+1+4)/5 = 10/5 = 2, 标准差 = √2
        assertEquals(Math.sqrt(2.0), result, DELTA);

        // 测试单个值
        result = (double) manager.invoke("stdDev", Arrays.asList(42));
        assertEquals(0.0, result, DELTA);

        // 测试空列表
        result = (double) manager.invoke("stdDev", Arrays.asList());
        assertEquals(0.0, result, DELTA);

        // 测试小数
        result = (double) manager.invoke("stdDev", Arrays.asList(1.5, 2.5, 3.5));
        // 平均值 = 2.5, 方差 = ((1.5-2.5)²+(2.5-2.5)²+(3.5-2.5)²)/3 = (1+0+1)/3 = 2/3 ≈ 0.6667
        // 标准差 = √(2/3) ≈ 0.816496580927726
        assertEquals(Math.sqrt(2.0 / 3.0), result, DELTA);
    }
    @Test
    public void testDivide() {
        double result = (double) manager.invoke("divide", Arrays.asList(10, 2));
        assertEquals(5.0, result, DELTA);

        result = (double) manager.invoke("divide", Arrays.asList(100, 2, 5));
        assertEquals(10.0, result, DELTA);

        result = (double) manager.invoke("divide", Arrays.asList(7.5, 2.5));
        assertEquals(3.0, result, DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        manager.invoke("divide", Arrays.asList(10, 0));
    }

    @Test
    public void testPow() {
        double result = (double) manager.invoke("pow", Arrays.asList(2, 3));
        assertEquals(8.0, result, DELTA);

        result = (double) manager.invoke("pow", Arrays.asList(4, 0.5));
        assertEquals(2.0, result, DELTA);

        result = (double) manager.invoke("pow", Arrays.asList(10, -2));
        assertEquals(0.01, result, DELTA);
    }

    @Test
    public void testMod() {
        double result = (double) manager.invoke("mod", Arrays.asList(10, 3));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("mod", Arrays.asList(15.5, 4.5));
        assertEquals(1.5, result, DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void testModByZero() {
        manager.invoke("mod", Arrays.asList(10, 0));
    }


    @Test
    public void testSin() {
        double result = (double) manager.invoke("sin", Arrays.asList(Math.PI / 2));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("sin", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testCos() {
        double result = (double) manager.invoke("cos", Arrays.asList(0));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("cos", Arrays.asList(Math.PI));
        assertEquals(-1.0, result, DELTA);
    }

    @Test
    public void testTan() {
        double result = (double) manager.invoke("tan", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testAsin() {
        double result = (double) manager.invoke("asin", Arrays.asList(1));
        assertEquals(Math.PI / 2, result, DELTA);

        result = (double) manager.invoke("asin", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testAcos() {
        double result = (double) manager.invoke("acos", Arrays.asList(1));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("acos", Arrays.asList(0));
        assertEquals(Math.PI / 2, result, DELTA);
    }

    @Test
    public void testAtan() {
        double result = (double) manager.invoke("atan", Arrays.asList(1));
        assertEquals(Math.PI / 4, result, DELTA);
    }

    @Test
    public void testAtan2() {
        double result = (double) manager.invoke("atan2", Arrays.asList(1, 1));
        assertEquals(Math.PI / 4, result, DELTA);

        result = (double) manager.invoke("atan2", Arrays.asList(0, 1));
        assertEquals(0.0, result, DELTA);
    }


    @Test
    public void testSinh() {
        double result = (double) manager.invoke("sinh", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testCosh() {
        double result = (double) manager.invoke("cosh", Arrays.asList(0));
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testTanh() {
        double result = (double) manager.invoke("tanh", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }


    @Test
    public void testExp() {
        double result = (double) manager.invoke("exp", Arrays.asList(0));
        assertEquals(1.0, result, DELTA);
        result = (double) manager.invoke("exp", Arrays.asList(1));
        assertEquals(Math.E, result, DELTA);
    }

    @Test
    public void testExpm1() {
        double result = (double) manager.invoke("expm1", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("expm1", Arrays.asList(1));
        assertEquals(Math.E - 1, result, DELTA);
    }

    @Test
    public void testLog() {
        double result = (double) manager.invoke("log", Arrays.asList(1));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("log", Arrays.asList(Math.E));
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testLog10() {
        double result = (double) manager.invoke("log10", Arrays.asList(100));
        assertEquals(2.0, result, DELTA);

        result = (double) manager.invoke("log10", Arrays.asList(10));
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testLog1p() {
        double result = (double) manager.invoke("log1p", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testSqrt() {
        double result = (double) manager.invoke("sqrt", Arrays.asList(16));
        assertEquals(4.0, result, DELTA);
    }

    @Test
    public void testCbrt() {
        double result = (double) manager.invoke("cbrt", Arrays.asList(27));
        assertEquals(3.0, result, DELTA);
    }


    @Test
    public void testCeil() {
        double result = (double) manager.invoke("ceil", Arrays.asList(3.14));
        assertEquals(4.0, result, DELTA);

        result = (double) manager.invoke("ceil", Arrays.asList(-3.14));
        assertEquals(-3.0, result, DELTA);
    }

    @Test
    public void testFloor() {
        double result = (double) manager.invoke("floor", Arrays.asList(3.14));
        assertEquals(3.0, result, DELTA);

        result = (double) manager.invoke("floor", Arrays.asList(-3.14));
        assertEquals(-4.0, result, DELTA);
    }

    @Test
    public void testRound() {
        long result = (long) manager.invoke("round", Arrays.asList(3.14));
        assertEquals(3L, result);
        result = (long) manager.invoke("round", Arrays.asList(3.5));
        assertEquals(4L, result);
    }

    @Test
    public void testCeilTo() {
        double result = (double) manager.invoke("ceilTo", Arrays.asList(3.14159, 2));
        assertEquals(3.15, result, DELTA);

        result = (double) manager.invoke("ceilTo", Arrays.asList(3.14159, 0));
        assertEquals(4.0, result, DELTA);
    }

    @Test
    public void testFloorTo() {
        double result = (double) manager.invoke("floorTo", Arrays.asList(3.14159, 2));
        assertEquals(3.14, result, DELTA);

        result = (double) manager.invoke("floorTo", Arrays.asList(3.14159, 0));
        assertEquals(3.0, result, DELTA);
    }

    @Test
    public void testRoundTo() {
        double result = (double) manager.invoke("roundTo", Arrays.asList(3.14159, 2));
        assertEquals(3.14, result, DELTA);

        result = (double) manager.invoke("roundTo", Arrays.asList(3.145, 2));
        assertEquals(3.15, result, DELTA);
    }


    @Test
    public void testMin() {
        double result = (double) manager.invoke("min", Arrays.asList(5, 3, 8, 1, 9));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("min", Arrays.asList(-5, -1, -3));
        assertEquals(-5.0, result, DELTA);
    }

    @Test
    public void testMax() {
        double result = (double) manager.invoke("max", Arrays.asList(5, 3, 8, 1, 9));
        assertEquals(9.0, result, DELTA);

        result = (double) manager.invoke("max", Arrays.asList(-5, -1, -3));
        assertEquals(-1.0, result, DELTA);
    }

    @Test
    public void testGreatest() {
        // greatest 返回原对象类型
        int result = (int) manager.invoke("greatest", Arrays.asList(5, 3, 8, 1, 9));
        assertEquals(9, result);

        String strResult = (String) manager.invoke("greatest", Arrays.asList("apple", "banana", "cherry"));
        assertEquals("cherry", strResult);
    }

    @Test
    public void testLeast() {
        int result = (int) manager.invoke("least", Arrays.asList(5, 3, 8, 1, 9));
        assertEquals(1, result);
        String strResult = (String) manager.invoke("least", Arrays.asList("apple", "banana", "cherry"));
        assertEquals("apple", strResult);
    }

    @Test
    public void testAvg() {
        double result = (double) manager.invoke("avg", Arrays.asList(1, 2, 3, 4));
        assertEquals(2.5, result, DELTA);

        result = (double) manager.invoke("avg", Arrays.asList());
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testMedian() {
        double result = (double) manager.invoke("median", Arrays.asList(1, 3, 2));
        assertEquals(2.0, result, DELTA);

        result = (double) manager.invoke("median", Arrays.asList(1, 2, 3, 4));
        assertEquals(2.5, result, DELTA);
    }

    @Test
    public void testMode() {
        double result = (double) manager.invoke("mode", Arrays.asList(1, 2, 2, 3, 3, 3));
        assertEquals(3.0, result, DELTA);
        result = (double) manager.invoke("mode", Arrays.asList(1, 1, 2, 2, 3, 3));
        assertEquals(1.0, result, DELTA); // 返回第一个遇到的众数
    }

    @Test
    public void testRange() {
        double result = (double) manager.invoke("range", Arrays.asList(5, 3, 8, 1, 9));
        assertEquals(8.0, result, DELTA);

        result = (double) manager.invoke("range", Arrays.asList());
        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void testPercentile() {
        double result = (double) manager.invoke("percentile", Arrays.asList(1, 2, 3, 4, 5, 50));
        assertEquals(3.0, result, DELTA);

        result = (double) manager.invoke("percentile", Arrays.asList(1, 2, 3, 4, 5, 25));
        assertEquals(2.0, result, DELTA);
    }


    @Test
    public void testPi() {
        double pi = (double) manager.invoke("pi", Arrays.asList());
        assertEquals(Math.PI, pi, DELTA);
    }

    @Test
    public void testE() {
        double e = (double) manager.invoke("e", Arrays.asList());
        assertEquals(Math.E, e, DELTA);
    }


    @Test
    public void testParseBinary() {
        long result = (long) manager.invoke("parseBinary", Arrays.asList("1010"));
        assertEquals(10L, result);

        result = (long) manager.invoke("parseBinary", Arrays.asList("11111111"));
        assertEquals(255L, result);
    }

    @Test
    public void testParseHex() {
        long result = (long) manager.invoke("parseHex", Arrays.asList("FF"));
        assertEquals(255L, result);

        result = (long) manager.invoke("parseHex", Arrays.asList("10"));
        assertEquals(16L, result);
    }

    @Test
    public void testToBinary() {
        String result = (String) manager.invoke("toBinary", Arrays.asList(10));
        assertEquals("1010", result);
    }

    @Test
    public void testToHex() {
        String result = (String) manager.invoke("toHex", Arrays.asList(255));
        assertEquals("ff", result);
    }
    @Test
    public void testToInt() {
        // 1个参数 - Number 类型
        int result = (int) manager.invoke("toInt", Arrays.asList(123));
        assertEquals(123, result);

        result = (int) manager.invoke("toInt", Arrays.asList(456L));
        assertEquals(456, result);

        result = (int) manager.invoke("toInt", Arrays.asList(3.14));
        assertEquals(3, result);

        result = (int) manager.invoke("toInt", Arrays.asList(3.99));
        assertEquals(3, result);

        result = (int) manager.invoke("toInt", Arrays.asList(-3.14));
        assertEquals(-3, result);

        result = (int) manager.invoke("toInt", Arrays.asList(123.0f));
        assertEquals(123, result);

        // 1个参数 - String 类型
        result = (int) manager.invoke("toInt", Arrays.asList("456"));
        assertEquals(456, result);

        result = (int) manager.invoke("toInt", Arrays.asList("-123"));
        assertEquals(-123, result);

        result = (int) manager.invoke("toInt", Arrays.asList("0"));
        assertEquals(0, result);


        result = (int) manager.invoke("toInt", Arrays.asList("+100"));
        assertEquals(100, result);



    }

    @Test
    public void testIsNumber() {
        assertTrue((boolean) manager.invoke("isNumber", Arrays.asList(123)));
        assertTrue((boolean) manager.invoke("isNumber", Arrays.asList(3.14)));
        assertTrue((boolean) manager.invoke("isNumber", Arrays.asList("123")));
        assertTrue((boolean) manager.invoke("isNumber", Arrays.asList("3.14")));
        assertTrue((boolean) manager.invoke("isNumber", Arrays.asList("-123")));

        assertFalse((boolean) manager.invoke("isNumber", Arrays.asList("abc")));
        assertFalse((boolean) manager.invoke("isNumber", Arrays.asList("12ab")));
       // assertFalse((boolean) manager.invoke("isNumber", Arrays.asList(null)));
    }

    @Test
    public void testIsEven() {
        assertTrue((boolean) manager.invoke("isEven", Arrays.asList(2)));
        assertTrue((boolean) manager.invoke("isEven", Arrays.asList(0)));
        assertFalse((boolean) manager.invoke("isEven", Arrays.asList(3)));
    }

    @Test
    public void testIsOdd() {
        assertTrue((boolean) manager.invoke("isOdd", Arrays.asList(3)));
        assertFalse((boolean) manager.invoke("isOdd", Arrays.asList(2)));
        assertFalse((boolean) manager.invoke("isOdd", Arrays.asList(0)));
    }

    @Test
    public void testIsPositive() {
        assertTrue((boolean) manager.invoke("isPositive", Arrays.asList(5)));
        assertFalse((boolean) manager.invoke("isPositive", Arrays.asList(0)));
        assertFalse((boolean) manager.invoke("isPositive", Arrays.asList(-5)));
    }

    @Test
    public void testIsNegative() {
        assertTrue((boolean) manager.invoke("isNegative", Arrays.asList(-5)));
        assertFalse((boolean) manager.invoke("isNegative", Arrays.asList(0)));
        assertFalse((boolean) manager.invoke("isNegative", Arrays.asList(5)));
    }

    @Test
    public void testIsZero() {
        assertTrue((boolean) manager.invoke("isZero", Arrays.asList(0)));
        assertTrue((boolean) manager.invoke("isZero", Arrays.asList(0.0)));
        assertFalse((boolean) manager.invoke("isZero", Arrays.asList(1)));
    }


    @Test
    public void testSignum() {
        double result = (double) manager.invoke("signum", Arrays.asList(10));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("signum", Arrays.asList(-5));
        assertEquals(-1.0, result, DELTA);

        result = (double) manager.invoke("signum", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);
    }
    @Test
    public void testToFloat() {
        // 1个参数 - Number 类型
        float result = (float) manager.invoke("toFloat", Arrays.asList(123));
        assertEquals(123.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(456L));
        assertEquals(456.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(3.14));
        assertEquals(3.14f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(3.14f));
        assertEquals(3.14f, result, DELTA);

        // 1个参数 - Boolean 类型
        result = (float) manager.invoke("toFloat", Arrays.asList(true));
        assertEquals(1.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(false));
        assertEquals(0.0f, result, DELTA);

        // 1个参数 - String 类型
        result = (float) manager.invoke("toFloat", Arrays.asList("456.78"));
        assertEquals(456.78f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList("-123.45"));
        assertEquals(-123.45f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList("0"));
        assertEquals(0.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList("  3.14  "));
        assertEquals(3.14f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList("123"));
        assertEquals(123.0f, result, DELTA);

        // 1个参数 - null 值（无默认值返回 0.0f）
        result = (float) manager.invoke("toFloat", Arrays.asList((Object) null));
        assertEquals(0.0f, result, DELTA);

        // 1个参数 - 空字符串（返回 0.0f）
        result = (float) manager.invoke("toFloat", Arrays.asList(""));
        assertEquals(0.0f, result, DELTA);

        // 2个参数 - null 值返回默认值
        result = (float) manager.invoke("toFloat", Arrays.asList(null, 999.0f));
        assertEquals(999.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(null, -100.5f));
        assertEquals(-100.5f, result, DELTA);

        // 2个参数 - 空字符串返回默认值
        result = (float) manager.invoke("toFloat", Arrays.asList("", 888.0f));
        assertEquals(888.0f, result, DELTA);

        // 2个参数 - 无效字符串返回默认值
        result = (float) manager.invoke("toFloat", Arrays.asList("invalid", 777.0f));
        assertEquals(777.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList("abc123", 666.0f));
        assertEquals(666.0f, result, DELTA);

        // 2个参数 - 有效值忽略默认值
        result = (float) manager.invoke("toFloat", Arrays.asList("123.45", 999.0f));
        assertEquals(123.45f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(true, 999.0f));
        assertEquals(1.0f, result, DELTA);

        result = (float) manager.invoke("toFloat", Arrays.asList(456, 999.0f));
        assertEquals(456.0f, result, DELTA);
    }

    @Test
    public void testToDegrees() {
        double result = (double) manager.invoke("toDegrees", Arrays.asList(Math.PI));
        assertEquals(180.0, result, DELTA);
    }
    @Test
    public void testToDouble() {
        // 1个参数 - Number 类型
        double result = (double) manager.invoke("toDouble", Arrays.asList(123));
        assertEquals(123.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList(456L));
        assertEquals(456.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList(3.14f));
        assertEquals(3.14, result, DELTA);

        // 1个参数 - Boolean 类型
        result = (double) manager.invoke("toDouble", Arrays.asList(true));
        assertEquals(1.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList(false));
        assertEquals(0.0, result, DELTA);

        // 1个参数 - String 类型
        result = (double) manager.invoke("toDouble", Arrays.asList("456.78"));
        assertEquals(456.78, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList("-123.45"));
        assertEquals(-123.45, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList("0"));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList("  3.14  "));
        assertEquals(3.14, result, DELTA);

        // 1个参数 - null 值（无默认值返回 0.0）
        result = (double) manager.invoke("toDouble", Arrays.asList((Object) null));
        assertEquals(0.0, result, DELTA);

        // 1个参数 - 空字符串（返回 0.0）
        result = (double) manager.invoke("toDouble", Arrays.asList(""));
        assertEquals(0.0, result, DELTA);

        // 2个参数 - null 值返回默认值
        result = (double) manager.invoke("toDouble", Arrays.asList(null, 999.0));
        assertEquals(999.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList(null, -100.5));
        assertEquals(-100.5, result, DELTA);

        // 2个参数 - 空字符串返回默认值
        result = (double) manager.invoke("toDouble", Arrays.asList("", 888.0));
        assertEquals(888.0, result, DELTA);

        // 2个参数 - 无效字符串返回默认值
        result = (double) manager.invoke("toDouble", Arrays.asList("invalid", 777.0));
        assertEquals(777.0, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList("abc123", 666.0));
        assertEquals(666.0, result, DELTA);

        // 2个参数 - 有效值忽略默认值
        result = (double) manager.invoke("toDouble", Arrays.asList("123.45", 999.0));
        assertEquals(123.45, result, DELTA);

        result = (double) manager.invoke("toDouble", Arrays.asList(true, 999.0));
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testToRadians() {
        double result = (double) manager.invoke("toRadians", Arrays.asList(180));
        assertEquals(Math.PI, result, DELTA);
    }
    @Test
    public void testVariance() {
        // 测试基本数据集
        double result = (double) manager.invoke("variance", Arrays.asList(1, 2, 3, 4, 5));
        // 平均值 = 3
        // 方差 = ((1-3)²+(2-3)²+(3-3)²+(4-3)²+(5-3)²)/5 = (4+1+0+1+4)/5 = 10/5 = 2
        assertEquals(2.0, result, DELTA);

        // 测试所有值相等
        result = (double) manager.invoke("variance", Arrays.asList(10, 10, 10, 10));
        assertEquals(0.0, result, DELTA);

        // 测试两个不同的值
        result = (double) manager.invoke("variance", Arrays.asList(1, 3));
        // 平均值 = 2, 方差 = ((1-2)²+(3-2)²)/2 = (1+1)/2 = 1
        assertEquals(1.0, result, DELTA);

        // 测试负数
        result = (double) manager.invoke("variance", Arrays.asList(-2, -1, 0, 1, 2));
        // 平均值 = 0, 方差 = (4+1+0+1+4)/5 = 10/5 = 2
        assertEquals(2.0, result, DELTA);

        // 测试单个值（方差为0）
        result = (double) manager.invoke("variance", Arrays.asList(42));
        assertEquals(0.0, result, DELTA);

        // 测试空列表（返回0.0）
        result = (double) manager.invoke("variance", Arrays.asList());
        assertEquals(0.0, result, DELTA);

        // 测试小数
        result = (double) manager.invoke("variance", Arrays.asList(1.5, 2.5, 3.5));
        // 平均值 = 2.5, 方差 = ((1.5-2.5)²+(2.5-2.5)²+(3.5-2.5)²)/3 = (1+0+1)/3 = 2/3 ≈ 0.6666666666666666
        assertEquals(2.0 / 3.0, result, DELTA);

        // 测试更大的数据集
        result = (double) manager.invoke("variance", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        // 平均值 = 5.5
        // 方差 = 8.25
        double expected = 8.25;
        assertEquals(expected, result, DELTA);
    }

    @Test
    public void testUlp() {
        // 正常值测试
        double result = (double) manager.invoke("ulp", Arrays.asList(1.0));
        assertEquals(Math.ulp(1.0), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(2.0));
        assertEquals(Math.ulp(2.0), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(10.0));
        assertEquals(Math.ulp(10.0), result, DELTA);

        // 零值
        result = (double) manager.invoke("ulp", Arrays.asList(0.0));
        assertEquals(Math.ulp(0.0), result, DELTA);

        // 负数
        result = (double) manager.invoke("ulp", Arrays.asList(-1.0));
        assertEquals(Math.ulp(-1.0), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(-3.14));
        assertEquals(Math.ulp(-3.14), result, DELTA);

        // 小数
        result = (double) manager.invoke("ulp", Arrays.asList(0.5));
        assertEquals(Math.ulp(0.5), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(0.1));
        assertEquals(Math.ulp(0.1), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(0.001));
        assertEquals(Math.ulp(0.001), result, DELTA);

        // 很小的正数
        result = (double) manager.invoke("ulp", Arrays.asList(Double.MIN_VALUE));
        assertEquals(Math.ulp(Double.MIN_VALUE), result, DELTA);

        // 很大的正数
        result = (double) manager.invoke("ulp", Arrays.asList(Double.MAX_VALUE));
        assertEquals(Math.ulp(Double.MAX_VALUE), result, DELTA);

        // 无穷大
        result = (double) manager.invoke("ulp", Arrays.asList(Double.POSITIVE_INFINITY));
        assertEquals(Double.POSITIVE_INFINITY, result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(Double.NEGATIVE_INFINITY));
        assertEquals(Double.POSITIVE_INFINITY, result, DELTA);

        // NaN
        result = (double) manager.invoke("ulp", Arrays.asList(Double.NaN));
        assertTrue(Double.isNaN(result));

        // 整数类型参数（自动转换为 double）
        result = (double) manager.invoke("ulp", Arrays.asList(100));
        assertEquals(Math.ulp(100.0), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList(-100));
        assertEquals(Math.ulp(-100.0), result, DELTA);

        // 字符串数字参数
        result = (double) manager.invoke("ulp", Arrays.asList("3.14"));
        assertEquals(Math.ulp(3.14), result, DELTA);

        result = (double) manager.invoke("ulp", Arrays.asList("-2.5"));
        assertEquals(Math.ulp(-2.5), result, DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAbsWithNoArgs() {
        manager.invoke("abs", Arrays.asList());
    }
    @Test()
    public void testPowWithNoArgs() {
        double result = (double) manager.invoke("pow", Arrays.asList(2,3));
        System.out.println(result);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivideWithNoArgs() {
        manager.invoke("divide", Arrays.asList());
    }


    @Test
    public void testSqrtEdgeCases() {
        double result = (double) manager.invoke("sqrt", Arrays.asList(0));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("sqrt", Arrays.asList(0.0001));
        assertEquals(0.01, result, DELTA);
    }

    @Test
    public void testPowEdgeCases() {
        double result = (double) manager.invoke("pow", Arrays.asList(0, 5));
        assertEquals(0.0, result, DELTA);

        result = (double) manager.invoke("pow", Arrays.asList(5, 0));
        assertEquals(1.0, result, DELTA);
    }

    @Test
    public void testComprehensiveMathScenario() {
        // 计算圆的面积和周长
        double radius = 5.0;
        double area = (double) manager.invoke("multiply", Arrays.asList(
                (double) manager.invoke("pi", Arrays.asList()),
                (double) manager.invoke("pow", Arrays.asList(radius, 2))
        ));
        double circumference = (double) manager.invoke("multiply", Arrays.asList(
                2, (double) manager.invoke("pi", Arrays.asList()), radius
        ));

        System.out.println("半径: " + radius);
        System.out.println("圆面积: " + area);
        System.out.println("圆周长: " + circumference);

        assertEquals(Math.PI * 25, area, DELTA);
        assertEquals(2 * Math.PI * 5, circumference, DELTA);
    }

    @Test
    public void testStatisticalScenario() {
        double[] scores = {85, 90, 78, 92, 88, 85, 91};

        double sum = (double) manager.invoke("add", Arrays.asList(
                scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6]
        ));
        double avg = (double) manager.invoke("avg", Arrays.asList(
                scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6]
        ));
        double max = (double) manager.invoke("max", Arrays.asList(
                scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6]
        ));
        double min = (double) manager.invoke("min", Arrays.asList(
                scores[0], scores[1], scores[2], scores[3], scores[4], scores[5], scores[6]
        ));

        System.out.println("成绩统计:");
        System.out.println("总分: " + sum);
        System.out.println("平均分: " + avg);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);

        assertEquals(609, sum, DELTA);
        assertEquals(87.0, avg, DELTA);
        assertEquals(92.0, max, DELTA);
        assertEquals(78.0, min, DELTA);
    }

    @Test
    public void testAngleConversionScenario() {
        // 角度弧度转换
        double degrees = 90;
        double radians = (double) manager.invoke("toRadians", Arrays.asList(degrees));
        double sinValue = (double) manager.invoke("sin", Arrays.asList(radians));

        System.out.println(degrees + "° = " + radians + " rad");
        System.out.println("sin(" + degrees + "°) = " + sinValue);

        assertEquals(Math.PI / 2, radians, DELTA);
        assertEquals(1.0, sinValue, DELTA);
    }
}