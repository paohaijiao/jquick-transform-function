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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickDayOfWeekInvoker extends JQuickBaseMethodInvoker {

    public JQuickDayOfWeekInvoker() {
        super("dayOfWeek", "获取星期几 - 用法: dayOfWeek(date?, locale?) 返回1-7(周一=1)或名称");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 2);

        LocalDate date;
        if (args.isEmpty()) {
            date = LocalDate.now();
        } else {
            Object dateObj = args.get(0);
            if (dateObj instanceof LocalDate) {
                date = (LocalDate) dateObj;
            } else if (dateObj instanceof LocalDateTime) {
                date = ((LocalDateTime) dateObj).toLocalDate();
            } else if (dateObj instanceof java.util.Date) {
                date = ((java.util.Date) dateObj).toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();
            } else {
                date = LocalDate.parse(dateObj.toString());
            }
        }

        boolean returnName = args.size() > 1 && "name".equals(asString(args.get(1)));

        if (returnName) {
            return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.CHINESE);
        }

        // 周一=1, 周日=7
        int dayOfWeek = date.getDayOfWeek().getValue();
        return dayOfWeek;
    }
}
