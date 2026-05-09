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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToDateTimeInvoker extends JQuickBaseMethodInvoker {

    public JQuickToDateTimeInvoker() {
        super("toDateTime", "转换为日期时间(LocalDateTime) - 用法: toDateTime(value, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        Object value = args.get(0);

        if (value == null) return null;

        if (value instanceof LocalDateTime) return value;
        if (value instanceof LocalDate) return ((LocalDate) value).atStartOfDay();
        if (value instanceof java.util.Date) {
            return ((java.util.Date) value).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
        }

        String str = value.toString();
        if (args.size() > 1) {
            String pattern = asString(args.get(1));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(str, formatter);
        }

        try {
            return LocalDateTime.parse(str);
        } catch (Exception e) {
            return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
