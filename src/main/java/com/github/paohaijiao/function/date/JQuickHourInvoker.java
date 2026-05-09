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
import java.time.LocalDateTime;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickHourInvoker extends JQuickBaseMethodInvoker {

    public JQuickHourInvoker() {
        super("hour", "获取小时(0-23) - 用法: hour(datetime?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        if (args.isEmpty()) {
            return LocalDateTime.now().getHour();
        }

        Object date = args.get(0);
        if (date instanceof LocalDateTime) {
            return ((LocalDateTime) date).getHour();
        } else if (date instanceof java.util.Date) {
            return ((java.util.Date) date).toInstant()
                    .atZone(java.time.ZoneId.systemDefault())
                    .toLocalDateTime().getHour();
        }

        LocalDateTime parsed = LocalDateTime.parse(date.toString());
        return parsed.getHour();
    }
}
