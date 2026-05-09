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
public class JQuickAreaTriangleInvoker extends JQuickBaseMethodInvoker {

    public JQuickAreaTriangleInvoker() {
        super("areaTriangle", "计算三角形面积 - 用法: areaTriangle(base, height) 或 areaTriangle(a, b, c)海伦公式");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() == 2) {
            double base = asDouble(args.get(0));
            double height = asDouble(args.get(1));
            return 0.5 * base * height;
        } else if (args.size() == 3) {
            double a = asDouble(args.get(0));
            double b = asDouble(args.get(1));
            double c = asDouble(args.get(2));
            double s = (a + b + c) / 2;
            return Math.sqrt(s * (s - a) * (s - b) * (s - c));
        }
        throw new IllegalArgumentException("参数个数必须为2或3");
    }
}
