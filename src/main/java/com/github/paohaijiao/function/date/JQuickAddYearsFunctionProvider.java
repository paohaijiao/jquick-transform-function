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
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickAddYearsFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickAddYearsFunctionProvider() {
        super("addYears", "增加年份 - 用法: addYears(date, years)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);

        Object dateObj = args.get(0);
        long years = asLong(args.get(1));

        if (dateObj instanceof LocalDate) {
            return ((LocalDate) dateObj).plusYears(years);
        } else if (dateObj instanceof LocalDateTime) {
            return ((LocalDateTime) dateObj).plusYears(years);
        }

        LocalDate parsed = LocalDate.parse(dateObj.toString());
        return parsed.plusYears(years);
    }
}
