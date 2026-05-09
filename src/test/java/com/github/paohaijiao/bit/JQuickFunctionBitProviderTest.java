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
    public void testIsBoolean() {
        assertTrue((boolean) manager.invoke("isBoolean", true));
        assertTrue((boolean) manager.invoke("isBoolean", false));
        assertFalse((boolean) manager.invoke("isBoolean", "true"));
        assertFalse((boolean) manager.invoke("isBoolean", 1));
        assertFalse((boolean) manager.invoke("isBoolean", new Object[]{null}));
    }

    @Test
    public void testIsEmail() {
        assertTrue((boolean) manager.invoke("isEmail", "test@example.com"));
        assertTrue((boolean) manager.invoke("isEmail", "user.name+tag@domain.co.uk"));
        assertFalse((boolean) manager.invoke("isEmail", "invalid-email"));
        assertFalse((boolean) manager.invoke("isEmail", "missing@domain"));
        assertFalse((boolean) manager.invoke("isEmail", new Object[]{null}));
    }

    @Test
    public void testBitAnd() {
        // 正确传递2个参数
        long result = (long) manager.invoke("bitAnd", 5, 3);
        assertEquals("5 & 3 = 1", 1L, result);

        result = (long) manager.invoke("bitAnd", 8, 4);
        assertEquals("8 & 4 = 0", 0L, result);

        // 字符串也会被转换为数字
        result = (long) manager.invoke("bitAnd", "5", "3");
        assertEquals(1L, result);

        // 更多测试用例
        result = (long) manager.invoke("bitAnd", 15, 7);
        assertEquals("15 & 7 = 7", 7L, result);

        result = (long) manager.invoke("bitAnd", 0xFF, 0x0F);
        assertEquals(0x0F, result);
    }

    @Test
    public void testBitOr() {
        long result = (long) manager.invoke("bitOr", 5, 3);
        assertEquals("5 | 3 = 7", 7L, result);

        result = (long) manager.invoke("bitOr", 8, 4);
        assertEquals("8 | 4 = 12", 12L, result);

        result = (long) manager.invoke("bitOr", 15, 7);
        assertEquals("15 | 7 = 15", 15L, result);

        result = (long) manager.invoke("bitOr", 0xFF, 0x00);
        assertEquals(0xFF, result);
    }

    @Test
    public void testBitXor() {
        long result = (long) manager.invoke("bitXor", 5, 3);
        assertEquals("5 ^ 3 = 6", 6L, result);

        result = (long) manager.invoke("bitXor", 8, 8);
        assertEquals("8 ^ 8 = 0", 0L, result);

        result = (long) manager.invoke("bitXor", 15, 7);
        assertEquals("15 ^ 7 = 8", 8L, result);

        result = (long) manager.invoke("bitXor", 0xFF, 0xFF);
        assertEquals(0L, result);
    }
}
