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
package com.github.paohaijiao.function.date;

/**
 * packageName com.github.paohaijiao.function.date
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickToIsoStringFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToIsoStringFunctionProvider() {
        super("toIsoString", "转ISO格式字符串 - 用法: toIsoString(date)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);

        Object dateObj = args.get(0);

        if (dateObj instanceof LocalDateTime) {
            return ((LocalDateTime) dateObj).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } else if (dateObj instanceof LocalDate) {
            return ((LocalDate) dateObj).format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else if (dateObj instanceof java.util.Date) {
            LocalDateTime ldt = ((java.util.Date) dateObj).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
            return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        return dateObj.toString();
    }
}
