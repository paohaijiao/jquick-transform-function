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
package com.github.paohaijiao.function.condition;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickCaseWhenFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCaseWhenFunctionProvider() {
        super("caseWhen", "CASE WHEN 条件表达式 - 用法: caseWhen(condition1, result1, condition2, result2, ..., defaultResult)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() < 2) {
            throw new IllegalArgumentException("至少需要2个参数");
        }

        for (int i = 0; i < args.size() - 1; i += 2) {
            boolean condition = asBoolean(args.get(i));
            if (condition) {
                return args.get(i + 1);
            }
        }
        if (args.size() % 2 == 1) {
            return args.get(args.size() - 1);
        }

        return null;
    }
}