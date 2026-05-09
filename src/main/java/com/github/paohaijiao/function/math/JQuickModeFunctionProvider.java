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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickModeFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickModeFunctionProvider() {
        super("mode", "计算众数（出现次数最多的数）- 用法: mode(numbers...)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return null;

        Map<Double, Integer> frequency = new HashMap<>();
        for (Object arg : args) {
            double num = asDouble(arg);
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        double mode = 0;
        int maxCount = 0;
        for (Map.Entry<Double, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }
}
