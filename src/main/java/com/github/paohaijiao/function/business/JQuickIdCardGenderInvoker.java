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
public class JQuickIdCardGenderInvoker extends JQuickBaseMethodInvoker {

    public JQuickIdCardGenderInvoker() {
        super("idCardGender", "从身份证号获取性别 - 用法: idCardGender(idCard, format?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        String idCard = asString(args.get(0));
        String format = args.size() > 1 ? asString(args.get(1)) : "中文";

        if (idCard == null) return null;

        idCard = idCard.trim();
        String genderCode;

        if (idCard.length() == 15) {
            genderCode = idCard.substring(14, 15);
        } else if (idCard.length() == 18) {
            genderCode = idCard.substring(16, 17);
        } else {
            throw new IllegalArgumentException("身份证号长度错误");
        }

        int code = Integer.parseInt(genderCode);
        boolean isMale = code % 2 == 1;

        switch (format.toLowerCase()) {
            case "中文": return isMale ? "男" : "女";
            case "mf": return isMale ? "M" : "F";
            case "10": return isMale ? "1" : "0";
            case "male/female": return isMale ? "male" : "female";
            default: return isMale ? "男" : "女";
        }
    }
}
