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

@Priority(PriorityConstants.SYSTEM_MEDIUM)
public class JQuickMatrixAddFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMatrixAddFunctionProvider() {
        super("matrixAdd", "矩阵加法 - 用法: matrixAdd(matrix1, matrix2)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        double[][] m1 = toMatrix(args.get(0));
        double[][] m2 = toMatrix(args.get(1));
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            throw new IllegalArgumentException("矩阵维度必须相同");
        }
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }
        return result;
    }

    private double[][] toMatrix(Object obj) {
        if (obj instanceof double[][]) return (double[][]) obj;
        if (obj instanceof List) {
            List<?> list = (List<?>) obj;
            double[][] matrix = new double[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                List<?> row = (List<?>) list.get(i);
                matrix[i] = new double[row.size()];
                for (int j = 0; j < row.size(); j++) {
                    matrix[i][j] = asDouble(row.get(j));
                }
            }
            return matrix;
        }
        throw new IllegalArgumentException("无法转换为矩阵");
    }
}
