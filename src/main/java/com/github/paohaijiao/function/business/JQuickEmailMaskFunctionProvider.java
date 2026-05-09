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

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickEmailMaskFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickEmailMaskFunctionProvider() {
        super("emailMask", "邮箱脱敏 - 用法: emailMask(email)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        String email = asString(args.get(0));

        if (email == null) return null;

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) return email;

        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);

        if (username.length() <= 2) {
            return "***" + domain;
        }

        String masked = username.charAt(0) + "***" + username.charAt(username.length() - 1);
        return masked + domain;
    }
}
