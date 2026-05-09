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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToShortInvoker extends JQuickBaseMethodInvoker {

    public JQuickToShortInvoker() {
        super("toShort", "转换为短整数 - 用法: toShort(value, defaultValue?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);

        try {
            Object value = args.get(0);
            if (value == null) {
                return (short) (args.size() > 1 ? asInt(args.get(1)) : 0);
            }

            if (value instanceof Number) {
                return ((Number) value).shortValue();
            }

            String str = value.toString().trim();
            if (str.isEmpty()) {
                return (short) (args.size() > 1 ? asInt(args.get(1)) : 0);
            }

            return Short.parseShort(str);
        } catch (Exception e) {
            if (args.size() > 1) {
                return (short) asInt(args.get(1));
            }
            throw new IllegalArgumentException("无法转换为短整数: " + args.get(0));
        }
    }
}
