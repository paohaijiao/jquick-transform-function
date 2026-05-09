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
import java.time.Year;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIsLeapYearInvoker extends JQuickBaseMethodInvoker {

    public JQuickIsLeapYearInvoker() {
        super("isLeapYear", "判断是否为闰年 - 用法: isLeapYear(year?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        int year;
        if (args.isEmpty()) {
            year = LocalDate.now().getYear();
        } else {
            year = asInt(args.get(0));
        }
        return Year.isLeap(year);
    }
}
