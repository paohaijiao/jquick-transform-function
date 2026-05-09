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
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAddDaysFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickAddDaysFunctionProvider() {
        super("addDays", "增加天数 - 用法: addDays(date, days)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);

        Object dateObj = args.get(0);
        long days = asLong(args.get(1));

        if (dateObj instanceof LocalDate) {
            return ((LocalDate) dateObj).plusDays(days);
        } else if (dateObj instanceof LocalDateTime) {
            return ((LocalDateTime) dateObj).plusDays(days);
        } else if (dateObj instanceof java.util.Date) {
            java.util.Date date = (java.util.Date) dateObj;
            return new java.util.Date(date.getTime() + days * 24 * 3600 * 1000);
        }

        LocalDate parsed = LocalDate.parse(dateObj.toString());
        return parsed.plusDays(days);
    }
}
