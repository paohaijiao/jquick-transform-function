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
package com.github.paohaijiao.bool;

import com.github.paohaijiao.function.manager.JQuickMethodInvocationManager;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * packageName com.github.paohaijiao.bool
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/10
 */
public class JQuickFunctionBoolProviderTest {
    private JQuickMethodInvocationManager manager;

    @Before
    public void setUp() {
        manager = JQuickMethodInvocationManager.getInstance();
    }

    @Test
    public void testIsBoolean() {
        assertTrue((boolean) manager.invoke("isBoolean", Arrays.asList(true)));
        assertTrue((boolean) manager.invoke("isBoolean", Arrays.asList(false)));
        assertFalse((boolean) manager.invoke("isBoolean", Arrays.asList("true")));
        assertFalse((boolean) manager.invoke("isBoolean", Arrays.asList(1)));
        assertFalse((boolean) manager.invoke("isBoolean", Arrays.asList((Object) null)));
    }
}
