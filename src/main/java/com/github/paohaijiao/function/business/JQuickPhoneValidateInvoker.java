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
import java.util.regex.Pattern;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickPhoneValidateInvoker extends JQuickBaseMethodInvoker {

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^1[3-9]\\d{9}$");

    public JQuickPhoneValidateInvoker() {
        super("phoneValidate", "校验手机号是否有效 - 用法: phoneValidate(phone)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String phone = asString(args.get(0));
        if (phone == null) return false;
        phone = phone.trim().replaceAll("\\s", "");
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
