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
package com.github.paohaijiao.function.math;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToFloatFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToFloatFunctionProvider() {
        super("toFloat", "转换为单精度浮点数 - 用法: toFloat(value, defaultValue?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);

        try {
            Object value = args.get(0);
            if (value == null) {
                return args.size() > 1 ? (float) asDouble(args.get(1)) : 0.0f;
            }

            if (value instanceof Number) {
                return ((Number) value).floatValue();
            }

            if (value instanceof Boolean) {
                return ((Boolean) value) ? 1.0f : 0.0f;
            }

            String str = value.toString().trim();
            if (str.isEmpty()) {
                return args.size() > 1 ? (float) asDouble(args.get(1)) : 0.0f;
            }

            return Float.parseFloat(str);
        } catch (Exception e) {
            if (args.size() > 1) {
                return (float) asDouble(args.get(1));
            }
            throw new IllegalArgumentException("无法转换为浮点数: " + args.get(0));
        }
    }
}
