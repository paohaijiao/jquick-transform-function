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
public class JQuickToLongFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToLongFunctionProvider() {
        super("toLong", "转换为长整数 - 用法: toLong(value, defaultValue?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        try {
            Object value = args.get(0);
            if (value == null) {
                return args.size() > 1 ? asLong(args.get(1)) : 0L;
            }
            if (value instanceof Number) {
                return ((Number) value).longValue();
            }
            if (value instanceof Boolean) {
                return ((Boolean) value) ? 1L : 0L;
            }
            String str = value.toString().trim();
            if (str.isEmpty()) {
                return args.size() > 1 ? asLong(args.get(1)) : 0L;
            }
            return Long.parseLong(str);
        } catch (Exception e) {
            if (args.size() > 1) {
                return asLong(args.get(1));
            }
            throw new IllegalArgumentException("无法转换为长整数: " + args.get(0));
        }
    }
}
