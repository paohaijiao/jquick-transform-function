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
public class JQuickCombinationFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickCombinationFunctionProvider() {
        super("combination", "计算组合数 C(n,k) - 用法: combination(n, k)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        int n = asInt(args.get(0));
        int k = asInt(args.get(1));
        if (k < 0 || k > n) return 0;
        long result = 1;
        k = Math.min(k, n - k);
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }
}
