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
package com.github.paohaijiao.trans;

/**
 * packageName com.github.paohaijiao.trans
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
import com.github.paohaijiao.param.JContext;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Translator 码值翻译函数提供者测试类
 * 测试范围：字典翻译、OGNL路径解析、默认值处理等
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickTranslatorFunctionProviderTest {

    private JQuickMethodInvocationManager manager;
    private JContext context;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
        context = new JContext();
        context.put("0", "待处理");
        context.put("1", "处理中");
        context.put("2", "已完成");
        context.put("3", "已取消");

        // 设置用户对象
        Map<String, Object> user = new HashMap<>();
        user.put("gender", "M");
        user.put("name", "张三");
        user.put("age", 25);
        context.put("user", user);

        // 设置订单对象
        Map<String, Object> order = new HashMap<>();
        order.put("status", "1");
        order.put("amount", 100.5);
        context.put("order", order);
    }

    @Test
    public void testTranslateDirectValue() {
        // 直接值翻译
        String result = null;

        // 订单状态翻译
        result = (String) manager.invoke("translate",
                Arrays.asList(context, "0"));
        assertEquals("待处理", result);

        result = (String) manager.invoke("translate",
                Arrays.asList(context, "1"));
        assertEquals("处理中", result);

        result = (String) manager.invoke("translate",
                Arrays.asList(context, "2"));
        assertEquals("已完成", result);

        result = (String) manager.invoke("translate",
                Arrays.asList(context, "3"));
        assertEquals("已取消", result);
    }



}
