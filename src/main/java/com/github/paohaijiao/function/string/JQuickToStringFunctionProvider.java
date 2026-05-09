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
package com.github.paohaijiao.function.string;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToStringFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToStringFunctionProvider() {
        super("toString", "转换为字符串 - 用法: toString(value, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        Object value = args.get(0);

        if (value == null) return null;

        // 日期类型特殊处理
        if (value instanceof LocalDate && args.size() > 1) {
            String pattern = asString(args.get(1));
            return ((LocalDate) value).format(DateTimeFormatter.ofPattern(pattern));
        }
        if (value instanceof LocalDateTime && args.size() > 1) {
            String pattern = asString(args.get(1));
            return ((LocalDateTime) value).format(DateTimeFormatter.ofPattern(pattern));
        }
        if (value instanceof java.util.Date && args.size() > 1) {
            String pattern = asString(args.get(1));
            LocalDateTime ldt = ((java.util.Date) value).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            return ldt.format(DateTimeFormatter.ofPattern(pattern));
        }

        return value.toString();
    }
}
