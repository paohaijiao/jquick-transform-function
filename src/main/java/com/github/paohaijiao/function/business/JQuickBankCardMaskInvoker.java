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

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickBankCardMaskInvoker extends JQuickBaseMethodInvoker {

    public JQuickBankCardMaskInvoker() {
        super("bankCardMask", "银行卡号脱敏 - 用法: bankCardMask(cardNo, keepStart?, keepEnd?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 3);
        String cardNo = asString(args.get(0));

        if (cardNo == null) return null;
        cardNo = cardNo.trim().replaceAll("\\s", "");

        int keepStart = args.size() > 1 ? asInt(args.get(1)) : 4;
        int keepEnd = args.size() > 2 ? asInt(args.get(2)) : 4;

        if (cardNo.length() <= keepStart + keepEnd) {
            return createStars(cardNo.length());

        }

        String start = cardNo.substring(0, keepStart);
        String end = cardNo.substring(cardNo.length() - keepEnd);
        String stars =createStars(cardNo.length() - keepStart - keepEnd);

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
