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
import java.time.temporal.ChronoUnit;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickHoursBetweenInvoker extends JQuickBaseMethodInvoker {

    public JQuickHoursBetweenInvoker() {
        super("hoursBetween", "计算两个日期时间之间的小时差 - 用法: hoursBetween(datetime1, datetime2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        LocalDateTime dt1 = toLocalDateTime(args.get(0));
        LocalDateTime dt2 = toLocalDateTime(args.get(1));

        return Math.abs(ChronoUnit.HOURS.between(dt1, dt2));
    }

    private LocalDateTime toLocalDateTime(Object obj) {
        if (obj instanceof LocalDateTime) return (LocalDateTime) obj;
        if (obj instanceof LocalDate) return ((LocalDate) obj).atStartOfDay();
        if (obj instanceof java.util.Date) {
            return ((java.util.Date) obj).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime();
        }
        return LocalDateTime.parse(obj.toString());
    }
}
