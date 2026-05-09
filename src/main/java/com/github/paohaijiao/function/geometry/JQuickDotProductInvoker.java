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
public class JQuickDotProductInvoker extends JQuickBaseMethodInvoker {

    public JQuickDotProductInvoker() {
        super("dot", "计算向量点积 - 用法: dot(vector1, vector2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);

        double[] v1 = toVector(args.get(0));
        double[] v2 = toVector(args.get(1));

        if (v1.length != v2.length) {
            throw new IllegalArgumentException("向量维度必须相同");
        }

        double result = 0;
        for (int i = 0; i < v1.length; i++) {
            result += v1[i] * v2[i];
        }
        return result;
    }

    private double[] toVector(Object obj) {
        if (obj instanceof double[]) return (double[]) obj;
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            double[] vector = new double[list.size()];
            for (int i = 0; i < list.size(); i++) {
                vector[i] = asDouble(list.get(i));
            }
            return vector;
        }
        throw new IllegalArgumentException("无法转换为向量");
    }
}
