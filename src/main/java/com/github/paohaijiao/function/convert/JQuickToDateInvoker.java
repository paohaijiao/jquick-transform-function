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
public class JQuickToDateInvoker extends JQuickBaseMethodInvoker {

    public JQuickToDateInvoker() {
        super("toDate", "转换为日期(LocalDate) - 用法: toDate(value, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        Object value = args.get(0);

        if (value == null) return null;

        if (value instanceof LocalDate) return value;
        if (value instanceof LocalDateTime) return ((LocalDateTime) value).toLocalDate();
        if (value instanceof java.util.Date) {
            return ((java.util.Date) value).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        }

        String str = value.toString();
        if (args.size() > 1) {
            String pattern = asString(args.get(1));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDate.parse(str, formatter);
        }

        try {
            return LocalDate.parse(str);
        } catch (Exception e) {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
