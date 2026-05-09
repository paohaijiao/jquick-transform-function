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
package com.github.paohaijiao.function.business;

/**
 * packageName com.github.paohaijiao.function.business
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIdCardBirthdayInvoker extends JQuickBaseMethodInvoker {

    public JQuickIdCardBirthdayInvoker() {
        super("idCardBirthday", "从身份证号提取生日 - 用法: idCardBirthday(idCard, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        String idCard = asString(args.get(0));

        if (idCard == null) return null;

        idCard = idCard.trim();
        String birthStr;

        if (idCard.length() == 15) {
            birthStr = "19" + idCard.substring(6, 12);
        } else if (idCard.length() == 18) {
            birthStr = idCard.substring(6, 14);
        } else {
            throw new IllegalArgumentException("身份证号长度错误");
        }

        LocalDate birthday = LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyyMMdd"));

        if (args.size() > 1) {
            String pattern = asString(args.get(1));
            return birthday.format(DateTimeFormatter.ofPattern(pattern));
        }
        return birthday;
    }
}
