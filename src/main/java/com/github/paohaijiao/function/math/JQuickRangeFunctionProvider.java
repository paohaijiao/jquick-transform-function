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
package com.github.paohaijiao.function.math;

/**
 * packageName com.github.paohaijiao.function.math
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
public class JQuickRangeFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRangeFunctionProvider() {
        super("range", "计算极差（最大值-最小值）- 用法: range(numbers...)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return 0.0;

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (Object arg : args) {
            double val = asDouble(arg);
            min = Math.min(min, val);
            max = Math.max(max, val);
        }
        return max - min;
    }
}
