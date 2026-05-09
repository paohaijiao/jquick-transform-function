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
public class JQuickCrossProductInvoker extends JQuickBaseMethodInvoker {

    public JQuickCrossProductInvoker() {
        super("cross", "计算向量叉积（2D标量）- 用法: cross(x1, y1, x2, y2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 4);
        double x1 = asDouble(args.get(0));
        double y1 = asDouble(args.get(1));
        double x2 = asDouble(args.get(2));
        double y2 = asDouble(args.get(3));

        return x1 * y2 - y1 * x2;
    }
}
