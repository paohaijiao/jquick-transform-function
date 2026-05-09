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
public class JQuickMapFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMapFunctionProvider() {
        super("map", "数值范围映射 - 用法: map(value, fromLow, fromHigh, toLow, toHigh, clamp?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 5, 6);
        double value = asDouble(args.get(0));
        double fromLow = asDouble(args.get(1));
        double fromHigh = asDouble(args.get(2));
        double toLow = asDouble(args.get(3));
        double toHigh = asDouble(args.get(4));
        boolean clamp = args.size() > 5 && asBoolean(args.get(5));

        if (fromLow == fromHigh) return toLow;

        double t = (value - fromLow) / (fromHigh - fromLow);
        if (clamp) t = Math.max(0, Math.min(1, t));
        return toLow + t * (toHigh - toLow);
    }
}
