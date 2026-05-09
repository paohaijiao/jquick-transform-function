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

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickDistanceFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickDistanceFunctionProvider() {
        super("distance", "计算两点间距离 - 用法: distance(x1, y1, x2, y2) 或 distance(x1, y1, z1, x2, y2, z2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() == 4) {
            double x1 = asDouble(args.get(0));
            double y1 = asDouble(args.get(1));
            double x2 = asDouble(args.get(2));
            double y2 = asDouble(args.get(3));
            return Math.hypot(x2 - x1, y2 - y1);
        } else if (args.size() == 6) {
            double x1 = asDouble(args.get(0));
            double y1 = asDouble(args.get(1));
            double z1 = asDouble(args.get(2));
            double x2 = asDouble(args.get(3));
            double y2 = asDouble(args.get(4));
            double z2 = asDouble(args.get(5));
            double dx = x2 - x1;
            double dy = y2 - y1;
            double dz = z2 - z1;
            return Math.sqrt(dx * dx + dy * dy + dz * dz);
        }
        throw new IllegalArgumentException("参数个数必须为4（2D）或6（3D）");
    }
}