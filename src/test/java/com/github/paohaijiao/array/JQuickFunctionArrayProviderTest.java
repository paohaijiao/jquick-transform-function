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
package com.github.paohaijiao.array;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * packageName com.github.paohaijiao.array
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionArrayProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }


    @Test
    public void testIsArray() {
        boolean result1 = (boolean) manager.invoke("isArray", (Object)(Arrays.asList(1, 2, 3)));
        assertTrue(result1);

        boolean result2 = (boolean) manager.invoke("isArray", (Object)new String[]{"a", "b"});
        assertTrue(result2);

        boolean result3 = (boolean) manager.invoke("isArray", "hello");
        assertFalse(result3);

        boolean result4 = (boolean) manager.invoke("isArray", (Object)(null));
        assertFalse(result4);
    }
    @Test
    public void testIsArray1() {
        // 测试 List - 单个参数
        List<String> list = Arrays.asList("a", "b");
        boolean result1 = (boolean) manager.invoke("isArray", list);
        assertTrue("List should be recognized as array", result1);

        // 测试 Object[] - 使用 (Object) 强制转换，防止数组被展开
        String[] array = new String[]{"a", "b"};
        boolean result2 = (boolean) manager.invoke("isArray", (Object) array);
        assertTrue("Object[] should be recognized as array", result2);

        // 测试非数组
        boolean result3 = (boolean) manager.invoke("isArray", "hello");
        assertFalse("String should not be recognized as array", result3);

        // 测试 null
        boolean result4 = (boolean) manager.invoke("isArray", new Object[]{null});
        assertFalse("null should not be recognized as array", result4);
    }
}
