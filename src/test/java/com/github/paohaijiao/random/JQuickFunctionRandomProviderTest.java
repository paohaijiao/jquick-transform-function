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
package com.github.paohaijiao.random;


import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Random 随机函数提供者测试类
 * 测试范围：randomBoolean, randomChoice, randomColor, randomDate, randomDouble,
 *          randomElement, randomIntArray, randomInt, randomLong, randomSample,
 *          shuffle, randomString, randomUUID
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionRandomProviderTest {

    private JQuickMethodInvocationManager manager;
    private static final double DELTA = 0.0001;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }


    @Test
    public void testRandomBoolean() {
        // 无参数 - 随机布尔值
        boolean result = (boolean) manager.invoke("randomBoolean", Arrays.asList());
        // 结果应该是 true 或 false，无法精确断言，只验证不抛异常
        assertTrue(result == true || result == false);
        // 带概率参数 - 概率为0
        result = (boolean) manager.invoke("randomBoolean", Arrays.asList(0.0));
        assertFalse(result);
        // 带概率参数 - 概率为1
        result = (boolean) manager.invoke("randomBoolean", Arrays.asList(1.0));
        assertTrue(result);
        // 带概率参数 - 概率0.5，多次测试统计
        int trueCount = 0;
        int total = 1000;
        for (int i = 0; i < total; i++) {
            if ((boolean) manager.invoke("randomBoolean", Arrays.asList(0.5))) {
                trueCount++;
            }
        }
        // 概率应该在 0.45-0.55 之间
        double ratio = trueCount / (double) total;
        assertTrue(ratio > 0.45 && ratio < 0.55);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomBooleanWithInvalidProbability() {
        manager.invoke("randomBoolean", Arrays.asList(1.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomBooleanWithNegativeProbability() {
        manager.invoke("randomBoolean", Arrays.asList(-0.5));
    }


    @Test
    public void testRandomChoice() {
        // 多个参数形式
        String result = (String) manager.invoke("randomChoice", Arrays.asList("A", "B", "C", "D"));
        assertNotNull(result);
        assertTrue(Arrays.asList("A", "B", "C", "D").contains(result));
        // List 参数形式
        List<String> list = Arrays.asList("apple", "banana", "cherry");
        result = (String) manager.invoke("randomChoice", Arrays.asList(list));
        assertNotNull(result);
        assertTrue(list.contains(result));
        // 单元素列表
        result = (String) manager.invoke("randomChoice", Arrays.asList("only"));
        assertEquals("only", result);
        // 数字类型
        Integer intResult = (Integer) manager.invoke("randomChoice", Arrays.asList(1, 2, 3, 4, 5));
        assertTrue(intResult >= 1 && intResult <= 5);
        // 空参数返回 null
        Object nullResult = manager.invoke("randomChoice", Arrays.asList());
        assertNull(nullResult);

        // 空列表返回 null
        nullResult = manager.invoke("randomChoice", Arrays.asList(new ArrayList<>()));
        assertNull(nullResult);
    }

    @Test
    public void testRandomColor() {
        // 无参数 - 默认 hex 格式
        String result = (String) manager.invoke("randomColor", Arrays.asList());
        assertTrue(result.matches("#[0-9A-Fa-f]{6}"));

        // hex 格式
        result = (String) manager.invoke("randomColor", Arrays.asList("hex"));
        assertTrue(result.matches("#[0-9A-Fa-f]{6}"));

        // rgb 格式
        result = (String) manager.invoke("randomColor", Arrays.asList("rgb"));
        assertTrue(result.matches("rgb\\(\\d{1,3}, \\d{1,3}, \\d{1,3}\\)"));
        // 验证 RGB 值范围
        String[] parts = result.replace("rgb(", "").replace(")", "").split(", ");
        int r = Integer.parseInt(parts[0]);
        int g = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        assertTrue(r >= 0 && r <= 255);
        assertTrue(g >= 0 && g <= 255);
        assertTrue(b >= 0 && b <= 255);

        // preset 格式（预设颜色）
        result = (String) manager.invoke("randomColor", Arrays.asList("preset"));
        assertNotNull(result);
        assertTrue(result.startsWith("#"));
    }
    @Test
    public void testRandomDate() {
        String startDate = "2024-01-01";
        String endDate = "2024-12-31";

        // 返回 LocalDate
        LocalDate result = (LocalDate) manager.invoke("randomDate", Arrays.asList(startDate, endDate));
        assertNotNull(result);
        assertTrue(result.isAfter(LocalDate.of(2023, 12, 31)) || result.isEqual(LocalDate.of(2024, 1, 1)));
        assertTrue(result.isBefore(LocalDate.of(2025, 1, 1)) || result.isEqual(LocalDate.of(2024, 12, 31)));

        // 带格式参数 - 返回格式化字符串
        String formattedResult = (String) manager.invoke("randomDate",
                Arrays.asList(startDate, endDate, "yyyy-MM-dd"));
        assertNotNull(formattedResult);
        assertTrue(formattedResult.matches("\\d{4}-\\d{2}-\\d{2}"));

        // 自定义格式
        formattedResult = (String) manager.invoke("randomDate",
                Arrays.asList(startDate, endDate, "yyyy/MM/dd"));
        assertTrue(formattedResult.matches("\\d{4}/\\d{2}/\\d{2}"));

        // 同一天
        LocalDate sameDay = (LocalDate) manager.invoke("randomDate", Arrays.asList("2024-06-01", "2024-06-01"));
        assertEquals(LocalDate.of(2024, 6, 1), sameDay);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomDateWithInvalidRange() {
        manager.invoke("randomDate", Arrays.asList("2024-12-31", "2024-01-01"));
    }


    @Test
    public void testRandomDouble() {
        // 无参数 - [0, 1)
        double result = (double) manager.invoke("randomDouble", Arrays.asList());
        assertTrue(result >= 0 && result < 1);

        // 1个参数 - [0, max)
        result = (double) manager.invoke("randomDouble", Arrays.asList(100.0));
        assertTrue(result >= 0 && result < 100);

        // 2个参数 - [min, max)
        result = (double) manager.invoke("randomDouble", Arrays.asList(10.0, 20.0));
        assertTrue(result >= 10 && result < 20);

        // 负值范围
        result = (double) manager.invoke("randomDouble", Arrays.asList(-20.0, -10.0));
        assertTrue(result >= -20 && result < -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomDoubleWithInvalidRange() {
        manager.invoke("randomDouble", Arrays.asList(10.0, 5.0));
    }

    @Test
    public void testRandomElement() {
        // 多个参数形式
        String result = (String) manager.invoke("random", Arrays.asList("A", "B", "C", "D"));
        assertNotNull(result);
        assertTrue(Arrays.asList("A", "B", "C", "D").contains(result));

        // 数组参数形式
        String[] arr = {"X", "Y", "Z"};
        result = (String) manager.invoke("random", Arrays.asList((Object) arr));
        assertTrue(Arrays.asList("X", "Y", "Z").contains(result));

        // List 参数形式
        List<String> list = Arrays.asList("red", "green", "blue");
        result = (String) manager.invoke("random", Arrays.asList(list));
        assertTrue(list.contains(result));

        // 空参数返回 null
        Object nullResult = manager.invoke("random", Arrays.asList());
        assertNull(nullResult);

        // 空数组返回 null
        String[] emptyArr = new String[0];
        nullResult = manager.invoke("random", Arrays.asList((Object) emptyArr));
        assertNull(nullResult);
    }


    @Test
    public void testRandomIntArray() {
        int[] result = (int[]) manager.invoke("randomIntArray", Arrays.asList(5, 1, 100));
        assertEquals(5, result.length);
        for (int value : result) {
            assertTrue(value >= 1 && value < 100);
        }

        // 大小=0
        result = (int[]) manager.invoke("randomIntArray", Arrays.asList(0, 1, 100));
        assertEquals(0, result.length);

        // 负大小返回空数组
        result = (int[]) manager.invoke("randomIntArray", Arrays.asList(-5, 1, 100));
        assertEquals(0, result.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomIntArrayWithInvalidRange() {
        manager.invoke("randomIntArray", Arrays.asList(5, 100, 1));
    }


    @Test
    public void testRandomInt() {
        // 无参数 - 任意整数
        int result = (int) manager.invoke("randomInt", Arrays.asList());
        // 无法精确断言，只验证不抛异常

        // 1个参数 - [0, max)
        result = (int) manager.invoke("randomInt", Arrays.asList(100));
        assertTrue(result >= 0 && result < 100);

        // 2个参数 - [min, max)
        result = (int) manager.invoke("randomInt", Arrays.asList(10, 20));
        assertTrue(result >= 10 && result < 20);

        // 负值范围
        result = (int) manager.invoke("randomInt", Arrays.asList(-20, -10));
        assertTrue(result >= -20 && result < -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomIntWithInvalidRange() {
        manager.invoke("randomInt", Arrays.asList(10, 5));
    }


    @Test
    public void testRandomLong() {
        // 无参数
        long result = (long) manager.invoke("randomLong", Arrays.asList());

        // 1个参数 - [0, max)
        result = (long) manager.invoke("randomLong", Arrays.asList(100L));
        assertTrue(result >= 0 && result < 100);

        // 2个参数 - [min, max)
        result = (long) manager.invoke("randomLong", Arrays.asList(10L, 20L));
        assertTrue(result >= 10 && result < 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomLongWithInvalidRange() {
        manager.invoke("randomLong", Arrays.asList(10L, 5L));
    }


    @Test
    public void testRandomSample() {
        List<String> source = Arrays.asList("A", "B", "C", "D", "E");
        // 不允许重复
        @SuppressWarnings("unchecked")
        List<String> sample = (List<String>) manager.invoke("randomSample", Arrays.asList(source, 3, false));
        assertEquals(3, sample.size());
        // 检查无重复
        assertEquals(3, sample.stream().distinct().count());
        // 允许重复
        sample = (List<String>) manager.invoke("randomSample", Arrays.asList(source, 10, true));
        assertEquals(10, sample.size());

        // 取样数量为0
        sample = (List<String>) manager.invoke("randomSample", Arrays.asList(source, 0));
        assertEquals(0, sample.size());


        // 默认不允许重复
        sample = (List<String>) manager.invoke("randomSample", Arrays.asList(source, 3));
        assertEquals(3, sample.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomSampleWithCountExceedSource() {
        List<String> source = Arrays.asList("A", "B", "C");
        manager.invoke("randomSample", Arrays.asList(source, 5, false));
    }


    @Test
    public void testShuffle() {
        // List 参数
        List<String> original = Arrays.asList("A", "B", "C", "D", "E");
        @SuppressWarnings("unchecked")
        List<String> shuffled = (List<String>) manager.invoke("shuffle", Arrays.asList(original));
        assertEquals(original.size(), shuffled.size());
        assertTrue(shuffled.containsAll(original));

        // 数组参数
        String[] arr = {"X", "Y", "Z"};
        @SuppressWarnings("unchecked")
        List<String> shuffledArr = (List<String>) manager.invoke("shuffle", Arrays.asList((Object) arr));
        assertEquals(3, shuffledArr.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShuffleWithInvalidParam() {
        manager.invoke("shuffle", Arrays.asList("not a list"));
    }
    @Test
    public void testRandomString() {
        // 固定长度
        String result = (String) manager.invoke("randomString", Arrays.asList(10));
        assertEquals(10, result.length());
        assertTrue(result.matches("[A-Za-z0-9]+"));

        // 长度为0
        result = (String) manager.invoke("randomString", Arrays.asList(0));
        assertEquals("", result);

        // 负长度
        result = (String) manager.invoke("randomString", Arrays.asList(-5));
        assertEquals("", result);

        // 多次生成应不同
        String result1 = (String) manager.invoke("randomString", Arrays.asList(20));
        String result2 = (String) manager.invoke("randomString", Arrays.asList(20));
        assertNotEquals(result1, result2);
    }
    @Test
    public void testRandomUUID() {
        // 无参数 - 带短横线
        String uuid = (String) manager.invoke("randomUUID", Arrays.asList());
        assertNotNull(uuid);
        assertEquals(36, uuid.length());
        assertTrue(uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"));

        // 不带短横线
        uuid = (String) manager.invoke("randomUUID", Arrays.asList(true));
        assertEquals(32, uuid.length());
        assertTrue(uuid.matches("[0-9a-f]{32}"));

        // 带短横线（false）
        uuid = (String) manager.invoke("randomUUID", Arrays.asList(false));
        assertEquals(36, uuid.length());
        assertTrue(uuid.contains("-"));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRandomIntArrayWithNoArgs() {
        manager.invoke("randomIntArray", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomSampleWithNoArgs() {
        manager.invoke("randomSample", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomStringWithNoArgs() {
        manager.invoke("randomString", Arrays.asList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShuffleWithNoArgs() {
        manager.invoke("shuffle", Arrays.asList());
    }


    @Test
    public void testRandomDataGenerationScenario() {
        // 生成随机用户数据
        int userId = (int) manager.invoke("randomInt", Arrays.asList(10000, 99999));
        int age = (int) manager.invoke("randomInt", Arrays.asList(18, 65));
        double score = (double) manager.invoke("randomDouble", Arrays.asList(0.0, 100.0));
        String name = (String) manager.invoke("randomString", Arrays.asList(8));
        boolean isActive = (boolean) manager.invoke("randomBoolean", Arrays.asList(0.7));

        System.out.println("=== 随机用户数据 ===");
        System.out.println("用户ID: " + userId);
        System.out.println("年龄: " + age);
        System.out.println("分数: " + score);
        System.out.println("用户名: " + name);
        System.out.println("是否活跃: " + isActive);

        assertTrue(userId >= 10000 && userId < 99999);
        assertTrue(age >= 18 && age < 65);
        assertTrue(score >= 0 && score < 100);
        assertEquals(8, name.length());
    }

    @Test
    public void testRandomColorPaletteScenario() {
        // 生成随机颜色调色板
        System.out.println("=== 随机颜色调色板 ===");

        String hexColor = (String) manager.invoke("randomColor", Arrays.asList("hex"));
        String rgbColor = (String) manager.invoke("randomColor", Arrays.asList("rgb"));
        String presetColor = (String) manager.invoke("randomColor", Arrays.asList("preset"));

        System.out.println("Hex 颜色: " + hexColor);
        System.out.println("RGB 颜色: " + rgbColor);
        System.out.println("预设颜色: " + presetColor);

        assertTrue(hexColor.startsWith("#"));
        assertTrue(rgbColor.startsWith("rgb("));
        assertTrue(presetColor.startsWith("#"));
    }

    @Test
    public void testRandomSampleScenario() {
        // 随机抽样场景
        List<String> candidates = Arrays.asList("张三", "李四", "王五", "赵六", "钱七", "孙八");

        // 随机抽取3个不重复的候选人
        @SuppressWarnings("unchecked")
        List<String> winners = (List<String>) manager.invoke("randomSample", Arrays.asList(candidates, 3, false));

        System.out.println("=== 随机抽奖 ===");
        System.out.println("候选人池: " + candidates);
        System.out.println("中奖者: " + winners);

        assertEquals(3, winners.size());
    }

    @Test
    public void testRandomUUIDGeneration() {
        // 生成多个UUID
        System.out.println("=== UUID 生成 ===");
        for (int i = 0; i < 5; i++) {
            String uuid = (String) manager.invoke("randomUUID", Arrays.asList());
            System.out.println("UUID " + (i + 1) + ": " + uuid);
            assertEquals(36, uuid.length());
        }

        // 无短横线版本
        for (int i = 0; i < 5; i++) {
            String uuid = (String) manager.invoke("randomUUID", Arrays.asList(true));
            System.out.println("Compact UUID " + (i + 1) + ": " + uuid);
            assertEquals(32, uuid.length());
        }
    }

    @Test
    public void testShuffleScenario() {
        // 洗牌场景
        List<String> deck = new ArrayList<>(Arrays.asList(
                "♠A", "♠2", "♠3", "♠4", "♠5", "♠6", "♠7", "♠8", "♠9", "♠10", "♠J", "♠Q", "♠K",
                "♥A", "♥2", "♥3", "♥4", "♥5", "♥6", "♥7", "♥8", "♥9", "♥10", "♥J", "♥Q", "♥K",
                "♣A", "♣2", "♣3", "♣4", "♣5", "♣6", "♣7", "♣8", "♣9", "♣10", "♣J", "♣Q", "♣K",
                "♦A", "♦2", "♦3", "♦4", "♦5", "♦6", "♦7", "♦8", "♦9", "♦10", "♦J", "♦Q", "♦K"
        ));

        @SuppressWarnings("unchecked")
        List<String> shuffledDeck = (List<String>) manager.invoke("shuffle", Arrays.asList(deck));

        System.out.println("=== 洗牌 ===");
        System.out.println("洗牌前第一张: " + deck.get(0));
        System.out.println("洗牌后第一张: " + shuffledDeck.get(0));
        System.out.println("洗牌后最后一张: " + shuffledDeck.get(shuffledDeck.size() - 1));

        assertEquals(deck.size(), shuffledDeck.size());
        assertTrue(shuffledDeck.containsAll(deck));
    }
}
