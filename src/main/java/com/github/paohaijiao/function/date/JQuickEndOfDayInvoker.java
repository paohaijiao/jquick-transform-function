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
import java.time.LocalTime;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickEndOfDayInvoker extends JQuickBaseMethodInvoker {

    public JQuickEndOfDayInvoker() {
        super("endOfDay", "获取日期结束时间(23:59:59) - 用法: endOfDay(date?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        LocalDate date;
        if (args.isEmpty()) {
            date = LocalDate.now();
        } else {
            date = toLocalDate(args.get(0));
        }

        return date.atTime(LocalTime.MAX);
    }

    private LocalDate toLocalDate(Object obj) {
        if (obj instanceof LocalDate) return (LocalDate) obj;
        if (obj instanceof LocalDateTime) return ((LocalDateTime) obj).toLocalDate();
        if (obj instanceof java.util.Date) {
            return ((java.util.Date) obj).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDate();
        }
        return LocalDate.parse(obj.toString());
    }
}
