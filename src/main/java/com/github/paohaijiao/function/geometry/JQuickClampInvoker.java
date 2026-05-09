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
package com.github.paohaijiao.function.geometry;

/**
 * packageName com.github.paohaijiao.function.geometry
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickClampInvoker extends JQuickBaseMethodInvoker {

    public JQuickClampInvoker() {
        super("clamp", "限制数值范围 - 用法: clamp(value, min, max)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 3);
        double value = asDouble(args.get(0));
        double min = asDouble(args.get(1));
        double max = asDouble(args.get(2));

        if (min > max) throw new IllegalArgumentException("min不能大于max");
        return Math.max(min, Math.min(max, value));
    }
}
