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
package com.github.paohaijiao.function.string;

/**
 * packageName com.github.paohaijiao.function.string
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickMaskEmailFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMaskEmailFunctionProvider() {
        super("maskEmail", "邮箱脱敏 - 用法: maskEmail(email) 例: te***@example.com");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String email = asString(args.get(0));
        if (email == null) return null;

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) return email;

        String local = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        if (local.length() <= 2) {
            return "***" + domain;
        }

        String masked = local.charAt(0)
                + new String(new char[Math.min(3, local.length() - 2)]).replace('\0', '*')
                + local.charAt(local.length() - 1);
        return masked + domain;
    }
}
