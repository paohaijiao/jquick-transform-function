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
package com.github.paohaijiao.collection;

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
public class JQuickFunctionCollectionConditionProviderTest {

    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testIsEmpty() {
        // String 测试
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList("")));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList("hello")));
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList((Object) null)));

        // Collection 测试
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList(new ArrayList<>())));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList(Arrays.asList(1, 2, 3))));

        // Map 测试
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList(new HashMap<>())));
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList(map)));

        // Array 测试
        assertTrue((boolean) manager.invoke("isEmpty", Arrays.asList((Object) new String[0])));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList((Object) new String[]{"a"})));

        // 其他对象
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList(123)));
        assertFalse((boolean) manager.invoke("isEmpty", Arrays.asList(true)));
    }

    @Test
    public void testSize() {
        // String 长度
        int size = (int) manager.invoke("size", Arrays.asList("hello"));
        assertEquals(5, size);

        // 空字符串
        size = (int) manager.invoke("size", Arrays.asList(""));
        assertEquals(0, size);

        // Collection 大小
        size = (int) manager.invoke("size", Arrays.asList(Arrays.asList(1, 2, 3)));
        assertEquals(3, size);

        // 空 Collection
        size = (int) manager.invoke("size", Arrays.asList(new ArrayList<>()));
        assertEquals(0, size);

        // Map 大小
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        size = (int) manager.invoke("size", Arrays.asList(map));
        assertEquals(2, size);

        // 空 Map
        size = (int) manager.invoke("size", Arrays.asList(new HashMap<>()));
        assertEquals(0, size);

        // Array 长度
        size = (int) manager.invoke("size", Arrays.asList((Object) new String[]{"a", "b", "c"}));
        assertEquals(3, size);

        // 空数组
        size = (int) manager.invoke("size", Arrays.asList((Object) new String[0]));
        assertEquals(0, size);

        // null 处理
        size = (int) manager.invoke("size", Arrays.asList((Object) null));
        assertEquals(0, size);

        // 不支持的类型应抛异常
        try {
            manager.invoke("size", Arrays.asList(123));
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("不支持获取长度"));
        }
    }

    @Test
    public void testJoin() {
        // 1个参数（无分隔符）
        String result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("a", "b", "c")));
        assertEquals("abc", result);

        // 2个参数（带分隔符）
        result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("a", "b", "c"), ","));
        assertEquals("a,b,c", result);

        // 不同分隔符
        result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("a", "b", "c"), "-"));
        assertEquals("a-b-c", result);

        result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("a", "b", "c"), " | "));
        assertEquals("a | b | c", result);

        // 数组作为参数
        String[] array = {"x", "y", "z"};
        result = (String) manager.invoke("join", Arrays.asList( array),",");
        assertEquals("x,y,z", result);

        // 数字列表
        result = (String) manager.invoke("join",Arrays.asList(1, 2, 3), ",");
        assertEquals("1,2,3", result);

        // 包含 null 的列表
        List<Object> listWithNull = Arrays.asList("a", null, "c");
        result = (String) manager.invoke("join", Arrays.asList(listWithNull, ","));
        assertEquals("a,,c", result);

        // 空集合
        result = (String) manager.invoke("join", Arrays.asList(new ArrayList<>(), ","));
        assertEquals("", result);

        // null 集合
        result = (String) manager.invoke("join", Arrays.asList((Object) null, ","));
        assertEquals("", result);

        // 单个元素
        result = (String) manager.invoke("join", Arrays.asList(Arrays.asList("single"), ","));
        assertEquals("single", result);
    }

}
