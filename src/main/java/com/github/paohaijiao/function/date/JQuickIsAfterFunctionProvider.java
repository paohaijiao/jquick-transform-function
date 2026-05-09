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
public class JQuickIsAfterFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickIsAfterFunctionProvider() {
        super("isAfter", "判断日期是否在之后 - 用法: isAfter(date1, date2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);

        LocalDate d1 = toLocalDate(args.get(0));
        LocalDate d2 = toLocalDate(args.get(1));

        return d1.isAfter(d2);
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
