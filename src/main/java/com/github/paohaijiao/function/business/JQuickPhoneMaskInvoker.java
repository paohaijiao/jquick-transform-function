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
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickPhoneMaskInvoker extends JQuickBaseMethodInvoker {

    public JQuickPhoneMaskInvoker() {
        super("phoneMask", "手机号脱敏 - 用法: phoneMask(phone, keepStart?, keepEnd?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 3);
        String phone = asString(args.get(0));

        if (phone == null) return null;
        phone = phone.trim().replaceAll("\\s", "");

        int keepStart = args.size() > 1 ? asInt(args.get(1)) : 3;
        int keepEnd = args.size() > 2 ? asInt(args.get(2)) : 4;

        if (phone.length() <= keepStart + keepEnd) {
            return phone;
        }

        String start = phone.substring(0, keepStart);
        String end = phone.substring(phone.length() - keepEnd);
        String stars = new String(new char[phone.length() - keepStart - keepEnd]).replace('\0', '*');
        return start + stars + end;
    }
    private String createStars(int count) {
        if (count <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }
        return sb.toString();
    }
}
