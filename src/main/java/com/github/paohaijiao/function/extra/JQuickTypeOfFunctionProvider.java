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
package com.github.paohaijiao.function.extra;

/**
 * packageName com.github.paohaijiao.function.extra
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickTypeOfFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickTypeOfFunctionProvider() {
        super("typeOf", "获取对象的类型名称 - 用法: typeOf(value)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        Object obj = args.get(0);

        if (obj == null) return "null";

        if (obj instanceof String) return "string";
        if (obj instanceof Number) return "number";
        if (obj instanceof Boolean) return "boolean";
        if (obj instanceof List) return "array";
        if (obj instanceof Map) return "object";
        if (obj instanceof Object[]) return "array";

        return obj.getClass().getSimpleName().toLowerCase();
    }
}