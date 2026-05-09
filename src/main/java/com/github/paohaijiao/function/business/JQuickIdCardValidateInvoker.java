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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIdCardValidateInvoker extends JQuickBaseMethodInvoker {

    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    public JQuickIdCardValidateInvoker() {
        super("idCardValidate", "校验身份证号是否有效 - 用法: idCardValidate(idCard)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String idCard = asString(args.get(0));
        if (idCard == null) return false;
        idCard = idCard.trim().toUpperCase();
        // 15位或18位
        if (!idCard.matches("^\\d{15}$|^\\d{17}[0-9X]$")) {
            return false;
        }
        // 15位转18位
        if (idCard.length() == 15) {
            idCard = convert15To18(idCard);
        }
        // 校验出生日期
        String birthStr = idCard.substring(6, 14);
        try {
            LocalDate.parse(birthStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e) {
            return false;
        }
        // 校验校验码
        return verifyCheckCode(idCard);
    }

    private boolean verifyCheckCode(String idCard) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (idCard.charAt(i) - '0') * WEIGHTS[i];
        }
        int mod = sum % 11;
        return CHECK_CODES[mod] == idCard.charAt(17);
    }

    private String convert15To18(String idCard15) {
        String idCard17 = idCard15.substring(0, 6) + "19" + idCard15.substring(6);
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (idCard17.charAt(i) - '0') * WEIGHTS[i];
        }
        int mod = sum % 11;
        return idCard17 + CHECK_CODES[mod];
    }
}
