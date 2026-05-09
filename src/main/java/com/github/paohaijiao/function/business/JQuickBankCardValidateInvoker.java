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
public class JQuickBankCardValidateInvoker extends JQuickBaseMethodInvoker {

    public JQuickBankCardValidateInvoker() {
        super("bankCardValidate", "校验银行卡号是否有效(Luhn算法) - 用法: bankCardValidate(cardNo)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String cardNo = asString(args.get(0));

        if (cardNo == null) return false;
        cardNo = cardNo.trim().replaceAll("\\s", "");

        if (!cardNo.matches("^\\d{16,19}$")) {
            return false;
        }

        return luhnCheck(cardNo);
    }

    private boolean luhnCheck(String cardNo) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardNo.length() - 1; i >= 0; i--) {
            int n = cardNo.charAt(i) - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
