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
package com.github.paohaijiao.function.convert;

/**
 * packageName com.github.paohaijiao.function.convert
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToBooleanFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToBooleanFunctionProvider() {
        super("toBoolean", "转换为布尔值 - 用法: toBoolean(value, defaultValue?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);

        Object value = args.get(0);

        if (value == null) {
            return args.size() > 1 && asBoolean(args.get(1));
        }

        if (value instanceof Boolean) {
            return value;
        }

        if (value instanceof Number) {
            return ((Number) value).doubleValue() != 0;
        }

        String str = value.toString().toLowerCase().trim();
        if ("true".equals(str) || "yes".equals(str) || "1".equals(str) || "on".equals(str)) {
            return true;
        }
        if ("false".equals(str) || "no".equals(str) || "0".equals(str) || "off".equals(str)) {
            return false;
        }

        if (args.size() > 1) {
            return asBoolean(args.get(1));
        }

        throw new IllegalArgumentException("无法转换为布尔值: " + args.get(0));
    }
}
