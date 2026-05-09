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
public class JQuickSwitchFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickSwitchFunctionProvider() {
        super("switch", "多分支匹配 - 用法: switch(value, case1, result1, case2, result2, ..., defaultValue)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() < 3 || args.size() % 2 == 0) {
            throw new IllegalArgumentException("参数个数必须为奇数，至少3个参数");
        }
        Object value = args.get(0);
        for (int i = 1; i < args.size() - 1; i += 2) {
            Object caseValue = args.get(i);
            if (value == null ? caseValue == null : value.equals(caseValue)) {
                return args.get(i + 1);
            }
        }

        return args.get(args.size() - 1);
    }
}
