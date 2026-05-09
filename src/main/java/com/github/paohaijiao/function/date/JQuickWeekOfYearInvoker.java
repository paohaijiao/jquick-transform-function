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

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickWeekOfYearInvoker extends JQuickBaseMethodInvoker {

    public JQuickWeekOfYearInvoker() {
        super("weekOfYear", "获取年中第几周 - 用法: weekOfYear(date?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        LocalDate date;
        if (args.isEmpty()) {
            date = LocalDate.now();
        } else {
            Object dateObj = args.get(0);
            if (dateObj instanceof LocalDate) {
                date = (LocalDate) dateObj;
            } else if (dateObj instanceof LocalDateTime) {
                date = ((LocalDateTime) dateObj).toLocalDate();
            } else {
                date = LocalDate.parse(dateObj.toString());
            }
        }

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        return date.get(weekFields.weekOfWeekBasedYear());
    }
}
