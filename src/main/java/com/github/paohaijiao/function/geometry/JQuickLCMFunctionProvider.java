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
public class JQuickLCMFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickLCMFunctionProvider() {
        super("lcm", "计算最小公倍数 - 用法: lcm(a, b, ...)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() < 2) throw new IllegalArgumentException("至少需要2个参数");

        long result = asLong(args.get(0));
        for (int i = 1; i < args.size(); i++) {
            result = lcm(result, asLong(args.get(i)));
        }
        return result;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * b) / gcd(a, b);
    }
}
