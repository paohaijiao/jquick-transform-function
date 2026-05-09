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
package com.github.paohaijiao.bit;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * packageName com.github.paohaijiao.array
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionBitProviderTest {
    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }
    @Test
    public void testBitAnd() {
        // 使用 List 传递2个参数
        long result = (long) manager.invoke("bitAnd", Arrays.asList(5, 3));
        assertEquals("5 & 3 = 1", 1L, result);

        result = (long) manager.invoke("bitAnd", Arrays.asList(8, 4));
        assertEquals("8 & 4 = 0", 0L, result);

        // 字符串也会被转换为数字
        result = (long) manager.invoke("bitAnd", Arrays.asList("5", "3"));
        assertEquals(1L, result);

        // 更多测试用例
        result = (long) manager.invoke("bitAnd", Arrays.asList(15, 7));
        assertEquals("15 & 7 = 7", 7L, result);

        result = (long) manager.invoke("bitAnd", Arrays.asList(0xFF, 0x0F));
        assertEquals(0x0F, result);
    }

    @Test
    public void testBitOr() {
        long result = (long) manager.invoke("bitOr", Arrays.asList(5, 3));
        assertEquals("5 | 3 = 7", 7L, result);

        result = (long) manager.invoke("bitOr", Arrays.asList(8, 4));
        assertEquals("8 | 4 = 12", 12L, result);

        result = (long) manager.invoke("bitOr", Arrays.asList(15, 7));
        assertEquals("15 | 7 = 15", 15L, result);

        result = (long) manager.invoke("bitOr", Arrays.asList(0xFF, 0x00));
        assertEquals(0xFF, result);
    }

    @Test
    public void testBitXor() {
        long result = (long) manager.invoke("bitXor", Arrays.asList(5, 3));
        assertEquals("5 ^ 3 = 6", 6L, result);

        result = (long) manager.invoke("bitXor", Arrays.asList(8, 8));
        assertEquals("8 ^ 8 = 0", 0L, result);

        result = (long) manager.invoke("bitXor", Arrays.asList(15, 7));
        assertEquals("15 ^ 7 = 8", 8L, result);

        result = (long) manager.invoke("bitXor", Arrays.asList(0xFF, 0xFF));
        assertEquals(0L, result);
    }
}
