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
package com.github.paohaijiao.geometry;

/**
 * packageName com.github.paohaijiao.geometry
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Geometry 几何/数学函数提供者测试类
 * 测试范围：面积、周长、距离、向量运算、矩阵运算、数论函数等
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionGeometryProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testAreaCircle() {
        // 半径 5
        double area = (double) manager.invoke("areaCircle", Arrays.asList(5.0));
        assertEquals(Math.PI * 25, area, 0.0001);
        // 半径 0
        area = (double) manager.invoke("areaCircle", Arrays.asList(0));
        assertEquals(0.0, area, 0.0001);
        // 整数半径
        area = (double) manager.invoke("areaCircle", Arrays.asList(3));
        assertEquals(Math.PI * 9, area, 0.0001);
    }

    @Test
    public void testAreaRectangle() {
        // 长宽 5 x 10
        double area = (double) manager.invoke("areaRectangle", Arrays.asList(5.0, 10.0));
        assertEquals(50.0, area, 0.0001);

        // 正方形
        area = (double) manager.invoke("areaRectangle", Arrays.asList(4, 4));
        assertEquals(16.0, area, 0.0001);

        // 零面积
        area = (double) manager.invoke("areaRectangle", Arrays.asList(5, 0));
        assertEquals(0.0, area, 0.0001);
    }

    @Test
    public void testAreaTriangle() {
        // 2个参数 - 底乘高除以2
        double area = (double) manager.invoke("areaTriangle", Arrays.asList(10.0, 5.0));
        assertEquals(25.0, area, 0.0001);

        // 3个参数 - 海伦公式
        area = (double) manager.invoke("areaTriangle", Arrays.asList(3.0, 4.0, 5.0));
        assertEquals(6.0, area, 0.0001);

        // 等边三角形
        area = (double) manager.invoke("areaTriangle", Arrays.asList(6.0, 6.0, 6.0));
        double expected = Math.sqrt(27) * 3; // 海伦公式: s=9, area=sqrt(9*3*3*3)=sqrt(243)=15.588
        assertEquals(15.588, area, 0.001);
    }

    @Test
    public void testCircumference() {
        // 半径 5
        double circ = (double) manager.invoke("circumference", Arrays.asList(5.0));
        assertEquals(2 * Math.PI * 5, circ, 0.0001);
        // 半径 0
        circ = (double) manager.invoke("circumference", Arrays.asList(0));
        assertEquals(0.0, circ, 0.0001);
    }
    @Test
    public void testClamp() {
        // 值在范围内
        double result = (double) manager.invoke("clamp", Arrays.asList(5.0, 1.0, 10.0));
        assertEquals(5.0, result, 0.0001);

        // 值小于最小值
        result = (double) manager.invoke("clamp", Arrays.asList(0.0, 1.0, 10.0));
        assertEquals(1.0, result, 0.0001);

        // 值大于最大值
        result = (double) manager.invoke("clamp", Arrays.asList(15.0, 1.0, 10.0));
        assertEquals(10.0, result, 0.0001);

        // 整数
        result = (double) manager.invoke("clamp", Arrays.asList(5, 1, 10));
        assertEquals(5.0, result, 0.0001);
    }


    @Test
    public void testLerp() {
        // t=0 返回 a
        double result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, 0.0));
        assertEquals(10.0, result, 0.0001);

        // t=1 返回 b
        result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, 1.0));
        assertEquals(20.0, result, 0.0001);

        // t=0.5 返回中间值
        result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, 0.5));
        assertEquals(15.0, result, 0.0001);

        // 负数 t
        result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, -0.5));
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testMap() {
        // 5个参数 - 不钳位
        double result = (double) manager.invoke("map", Arrays.asList(50.0, 0.0, 100.0, 0.0, 200.0));
        assertEquals(100.0, result, 0.0001);

        // 超出范围 - 不钳位
        result = (double) manager.invoke("map", Arrays.asList(150.0, 0.0, 100.0, 0.0, 200.0));
        assertEquals(300.0, result, 0.0001);

        // 6个参数 - 钳位
        result = (double) manager.invoke("map", Arrays.asList(150.0, 0.0, 100.0, 0.0, 200.0, true));
        assertEquals(200.0, result, 0.0001);

        // 反向映射
        result = (double) manager.invoke("map", Arrays.asList(25.0, 0.0, 100.0, 100.0, 0.0));
        assertEquals(75.0, result, 0.0001);
    }

    // ==================== 距离/向量运算测试 ====================

    @Test
    public void testDistance() {
        // 2D 距离
        double dist = (double) manager.invoke("distance", Arrays.asList(0.0, 0.0, 3.0, 4.0));
        assertEquals(5.0, dist, 0.0001);

        // 3D 距离
        dist = (double) manager.invoke("distance", Arrays.asList(0.0, 0.0, 0.0, 1.0, 2.0, 2.0));
        assertEquals(3.0, dist, 0.0001);
    }

    @Test
    public void testHypot() {
        double result = (double) manager.invoke("hypot", Arrays.asList(3.0, 4.0));
        assertEquals(5.0, result, 0.0001);

        result = (double) manager.invoke("hypot", Arrays.asList(6.0, 8.0));
        assertEquals(10.0, result, 0.0001);
    }

    @Test
    public void testCross() {
        // 二维向量叉积 (标量)
        double cross = (double) manager.invoke("cross", Arrays.asList(1.0, 0.0, 0.0, 1.0));
        assertEquals(1.0, cross, 0.0001);

        cross = (double) manager.invoke("cross", Arrays.asList(2.0, 3.0, 4.0, 5.0));
        assertEquals(2*5 - 3*4, cross, 0.0001);
    }

    @Test
    public void testDot() {
        // 使用数组作为向量
        double[] v1 = {1.0, 2.0, 3.0};
        double[] v2 = {4.0, 5.0, 6.0};
        double dot = (double) manager.invoke("dot", Arrays.asList(v1, v2));
        assertEquals(1*4 + 2*5 + 3*6, dot, 0.0001);
        // 使用 List 作为向量
        List<Double> list1 = Arrays.asList(1.0, 2.0);
        List<Double> list2 = Arrays.asList(3.0, 4.0);
        dot = (double) manager.invoke("dot", Arrays.asList(list1, list2));
        assertEquals(1*3 + 2*4, dot, 0.0001);
    }

    @Test
    public void testComplexAdd() {
        double[] result = (double[]) manager.invoke("complexAdd", Arrays.asList(1.0, 2.0, 3.0, 4.0));
        assertEquals(4.0, result[0], 0.0001);
        assertEquals(6.0, result[1], 0.0001);
    }

    @Test
    public void testComplexMultiply() {
        // (1+2i) * (3+4i) = (1*3-2*4) + (1*4+2*3)i = -5 + 10i
        double[] result = (double[]) manager.invoke("complexMultiply", Arrays.asList(1.0, 2.0, 3.0, 4.0));
        assertEquals(-5.0, result[0], 0.0001);
        assertEquals(10.0, result[1], 0.0001);
    }

    @Test
    public void testMatrixAdd() {
        double[][] m1 = {{1, 2}, {3, 4}};
        double[][] m2 = {{5, 6}, {7, 8}};
        double[][] result = (double[][]) manager.invoke("matrixAdd", Arrays.asList(m1, m2));
        assertEquals(6.0, result[0][0], 0.0001);
        assertEquals(8.0, result[0][1], 0.0001);
        assertEquals(10.0, result[1][0], 0.0001);
        assertEquals(12.0, result[1][1], 0.0001);
        // 使用 List 作为矩阵
        List<List<Integer>> list1 = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        );
        List<List<Integer>> list2 = Arrays.asList(
                Arrays.asList(5, 6),
                Arrays.asList(7, 8)
        );
        result = (double[][]) manager.invoke("matrixAdd", Arrays.asList(list1, list2));
        assertEquals(6.0, result[0][0], 0.0001);
    }
    @Test
    public void testFactorial() {
        long result = (long) manager.invoke("factorial", Arrays.asList(5));
        assertEquals(120, result);
        result = (long) manager.invoke("factorial", Arrays.asList(0));
        assertEquals(1, result);
        result = (long) manager.invoke("factorial", Arrays.asList(1));
        assertEquals(1, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFactorialNegative() {
        manager.invoke("factorial", Arrays.asList(-1));
    }

    @Test
    public void testFibonacci() {
        long result = (long) manager.invoke("fibonacci", Arrays.asList(0));
        assertEquals(0, result);
        result = (long) manager.invoke("fibonacci", Arrays.asList(1));
        assertEquals(1, result);
        result = (long) manager.invoke("fibonacci", Arrays.asList(2));
        assertEquals(1, result);
        result = (long) manager.invoke("fibonacci", Arrays.asList(6));
        assertEquals(8, result);
        result = (long) manager.invoke("fibonacci", Arrays.asList(10));
        assertEquals(55, result);
    }

    @Test
    public void testCombination() {
        long result = (long) manager.invoke("combination", Arrays.asList(5, 2));
        assertEquals(10, result);
        result = (long) manager.invoke("combination", Arrays.asList(5, 0));
        assertEquals(1, result);
        result = (long) manager.invoke("combination", Arrays.asList(5, 5));
        assertEquals(1, result);
        result = (int) manager.invoke("combination", Arrays.asList(5, 6));
        assertEquals(0, result);
    }

    @Test
    public void testPermutation() {
        long result = (long) manager.invoke("permutation", Arrays.asList(5, 2));
        assertEquals(20, result);  // 5*4=20

        result = (long) manager.invoke("permutation", Arrays.asList(5, 0));
        assertEquals(1, result);

        result = (int) manager.invoke("permutation", Arrays.asList(5, 6));
        assertEquals(0, result);
    }

    @Test
    public void testGcd() {
        // 2个参数
        long result = (long) manager.invoke("gcd", Arrays.asList(12, 18));
        assertEquals(6, result);
        // 3个参数
        result = (long) manager.invoke("gcd", Arrays.asList(12, 18, 24));
        assertEquals(6, result);
        // 多个参数
        result = (long) manager.invoke("gcd", Arrays.asList(8, 12, 16, 20));
        assertEquals(4, result);
    }

    @Test
    public void testLcm() {
        long result = (long) manager.invoke("lcm", Arrays.asList(4, 6));
        assertEquals(12, result);

        result = (long) manager.invoke("lcm", Arrays.asList(4, 6, 8));
        assertEquals(24, result);
    }

    @Test
    public void testIsPrime() {
        // 素数
        assertTrue((boolean) manager.invoke("isPrime", Arrays.asList(2)));
        assertTrue((boolean) manager.invoke("isPrime", Arrays.asList(3)));
        assertTrue((boolean) manager.invoke("isPrime", Arrays.asList(17)));
        assertTrue((boolean) manager.invoke("isPrime", Arrays.asList(97)));

        // 非素数
        assertFalse((boolean) manager.invoke("isPrime", Arrays.asList(1)));
        assertFalse((boolean) manager.invoke("isPrime", Arrays.asList(4)));
        assertFalse((boolean) manager.invoke("isPrime", Arrays.asList(100)));
    }

    @Test
    public void testIsPowerOfTwo() {
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(1)));
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(2)));
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(4)));
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(8)));
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(16)));
        assertTrue((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(1024)));

        assertFalse((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(0)));
        assertFalse((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(3)));
        assertFalse((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(5)));
        assertFalse((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(6)));
        assertFalse((boolean) manager.invoke("isPowerOfTwo", Arrays.asList(10)));
    }

    // ==================== 参数校验测试 ====================

    @Test(expected = IllegalArgumentException.class)
    public void testAreaCircleWithNoArgs() {
        manager.invoke("areaCircle", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAreaRectangleWithNoArgs() {
        manager.invoke("areaRectangle", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGcdWithNoArgs() {
        manager.invoke("gcd", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGcdWithOneArg() {
        manager.invoke("gcd", Arrays.asList(12));
    }

    // ==================== 边界条件测试 ====================

    @Test
    public void testDistanceEdgeCases() {
        // 同一点距离为0
        double dist = (double) manager.invoke("distance", Arrays.asList(1.0, 2.0, 1.0, 2.0));
        assertEquals(0.0, dist, 0.0001);

        // 3D 同一点
        dist = (double) manager.invoke("distance", Arrays.asList(1.0, 2.0, 3.0, 1.0, 2.0, 3.0));
        assertEquals(0.0, dist, 0.0001);
    }

    @Test
    public void testFactorialLarge() {
        long result = (long) manager.invoke("factorial", Arrays.asList(20));
        assertEquals(2432902008176640000L, result);
    }

    @Test
    public void testCombinationLarge() {
        long result = (long) manager.invoke("combination", Arrays.asList(10, 5));
        assertEquals(252, result);

        result = (long) manager.invoke("combination", Arrays.asList(10, 3));
        assertEquals(120, result);
    }

    @Test
    public void testMapEdgeCases() {
        // fromLow == fromHigh 时返回 toLow
        double result = (double) manager.invoke("map", Arrays.asList(50.0, 100.0, 100.0, 0.0, 200.0));
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testLerpEdgeCases() {
        double result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, -1.0));
        assertEquals(-10.0, result, 0.0001);

        result = (double) manager.invoke("lerp", Arrays.asList(10.0, 20.0, 2.0));
        assertEquals(30.0, result, 0.0001);
    }

    // ==================== 综合场景测试 ====================

    @Test
    public void testGeometryCalculationScenario() {
        // 计算圆的面积和周长
        double radius = 5.0;
        double area = (double) manager.invoke("areaCircle", Arrays.asList(radius));
        double circumference = (double) manager.invoke("circumference", Arrays.asList(radius));

        System.out.println("半径: " + radius);
        System.out.println("圆面积: " + area);
        System.out.println("圆周长: " + circumference);

        assertEquals(Math.PI * 25, area, 0.0001);
        assertEquals(2 * Math.PI * 5, circumference, 0.0001);
    }

    @Test
    public void testTriangleAreaComparison() {
        // 比较两种三角形面积计算方法
        double base = 10.0;
        double height = 5.0;
        double area1 = (double) manager.invoke("areaTriangle", Arrays.asList(base, height));

        double a = 10.0, b = 10.0, c = 10.0;
        double area2 = (double) manager.invoke("areaTriangle", Arrays.asList(a, b, c));

        System.out.println("底乘高法面积: " + area1);
        System.out.println("等边三角形海伦公式面积: " + area2);
    }

    @Test
    public void testVectorOperationScenario() {
        // 向量运算场景
        double[] v1 = {3.0, 4.0};
        double[] v2 = {1.0, 2.0};
        double dot = (double) manager.invoke("dot", Arrays.asList(v1, v2));
        double cross = (double) manager.invoke("cross", Arrays.asList(v1[0], v1[1], v2[0], v2[1]));
        double length = (double) manager.invoke("hypot", Arrays.asList(v1[0], v1[1]));
        System.out.println("向量点积: " + dot);
        System.out.println("向量叉积: " + cross);
        System.out.println("向量长度: " + length);
        assertEquals(3*1 + 4*2, dot, 0.0001);
        assertEquals(3*2 - 4*1, cross, 0.0001);
        assertEquals(5.0, length, 0.0001);
    }

    @Test
    public void testValueMappingScenario() {
        // 将 0-100 的成绩映射到 0-5 的绩点
        double score = 85.0;
        double gpa = (double) manager.invoke("map", Arrays.asList(score, 0.0, 100.0, 0.0, 4.0));
        System.out.println("成绩 " + score + " 分，绩点: " + gpa);

        // 限制范围
        score = 120.0;
        double clampedGpa = (double) manager.invoke("map", Arrays.asList(score, 0.0, 100.0, 0.0, 4.0, true));
        System.out.println("成绩 " + score + " 分，限制后绩点: " + clampedGpa);
        assertEquals(4.0, clampedGpa, 0.0001);
    }

    @Test
    public void testNumberTheoryScenario() {
        // 数论计算场景
        int n = 12;
        long fact = (long) manager.invoke("factorial", Arrays.asList(n));
        long fib = (long) manager.invoke("fibonacci", Arrays.asList(n));
        boolean isPrime = (boolean) manager.invoke("isPrime", Arrays.asList(n));
        boolean isPowerOfTwo = (boolean) manager.invoke("isPowerOfTwo", Arrays.asList(n));
        System.out.println(n + "! = " + fact);
        System.out.println("Fibonacci(" + n + ") = " + fib);
        System.out.println(n + " 是素数: " + isPrime);
        System.out.println(n + " 是2的幂: " + isPowerOfTwo);
    }

    @Test
    public void testGcdLcmScenario() {
        int numerator = 48;
        int denominator = 60;
        long gcd = (long) manager.invoke("gcd", Arrays.asList(numerator, denominator));
        long lcm = (long) manager.invoke("lcm", Arrays.asList(numerator, denominator));
        long simplifiedNumerator = numerator / gcd;
        long simplifiedDenominator = denominator / gcd;
        System.out.println("原分数: " + numerator + "/" + denominator);
        System.out.println("最大公约数: " + gcd);
        System.out.println("最小公倍数: " + lcm);
        System.out.println("简化后: " + simplifiedNumerator + "/" + simplifiedDenominator);

        assertEquals(12, gcd);
        assertEquals(240, lcm);
        assertEquals(4, simplifiedNumerator);
        assertEquals(5, simplifiedDenominator);
    }

    @Test
    public void testComprehensiveMathScenario() {
        // 综合数学计算
        double[] point1 = {0, 0};
        double[] point2 = {3, 4};
        // 计算距离
        double distance = (double) manager.invoke("distance", Arrays.asList(point1[0], point1[1], point2[0], point2[1]));

        // 限制范围
        double clamped = (double) manager.invoke("clamp", Arrays.asList(distance, 0.0, 10.0));

        // 线性插值
        double lerpValue = (double) manager.invoke("lerp", Arrays.asList(0.0, distance, 0.5));

        System.out.println("两点距离: " + distance);
        System.out.println("限制后: " + clamped);
        System.out.println("中点值: " + lerpValue);

        assertEquals(5.0, distance, 0.0001);
        assertEquals(5.0, clamped, 0.0001);
        assertEquals(2.5, lerpValue, 0.0001);
    }
}