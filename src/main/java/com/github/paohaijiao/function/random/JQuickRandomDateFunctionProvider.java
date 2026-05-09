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
package com.github.paohaijiao.function.random;

/**
 * packageName com.github.paohaijiao.function.random
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickRandomDateFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRandomDateFunctionProvider() {
        super("randomDate", "生成随机日期 - 用法: randomDate(startDate, endDate, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);

        LocalDate start = parseDate(asString(args.get(0)));
        LocalDate end = parseDate(asString(args.get(1)));

        if (start.isAfter(end)) {
            throw new IllegalArgumentException("开始日期不能晚于结束日期");
        }

        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        long randomDays = ThreadLocalRandom.current().nextLong(daysBetween + 1);
        LocalDate randomDate = start.plusDays(randomDays);

        if (args.size() > 2) {
            String pattern = asString(args.get(2));
            return randomDate.format(DateTimeFormatter.ofPattern(pattern));
        }
        return randomDate;
    }

    private LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (Exception e) {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }
}
