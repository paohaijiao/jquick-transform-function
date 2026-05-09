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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.Arrays;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickPercentileInvoker extends JQuickBaseMethodInvoker {

    public JQuickPercentileInvoker() {
        super("percentile", "计算百分位数 - 用法: percentile(numbers..., percentile)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.size() < 2) throw new IllegalArgumentException("至少需要一组数和百分位值");
        double[] values = args.subList(0, args.size() - 1)
                .stream()
                .mapToDouble(this::asDouble)
                .toArray();
        double p = asDouble(args.get(args.size() - 1));
        if (p < 0 || p > 100) throw new IllegalArgumentException("百分位必须在0-100之间");
        Arrays.sort(values);
        double index = (p / 100.0) * (values.length - 1);
        int lower = (int) Math.floor(index);
        int upper = (int) Math.ceil(index);
        if (lower == upper) return values[lower];
        return values[lower] + (values[upper] - values[lower]) * (index - lower);
    }
}
