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
public class JQuickBetweenFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickBetweenFunctionProvider() {
        super("between", "范围判断 - 用法: between(value, min, max, inclusive?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 3, 4);
        double value = asDouble(args.get(0));
        double min = asDouble(args.get(1));
        double max = asDouble(args.get(2));
        boolean inclusive = args.size() > 3 ? asBoolean(args.get(3)) : true;

        if (inclusive) {
            return value >= min && value <= max;
        } else {
            return value > min && value < max;
        }
    }
}
