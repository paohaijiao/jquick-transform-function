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
package com.github.paohaijiao.function.random;

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.Random;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRandomIntFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    private static final Random RANDOM = new Random();

    public JQuickRandomIntFunctionProvider() {
        super("randomInt", "生成随机整数 - 用法: randomInt() 或 randomInt(max) 或 randomInt(min, max)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 2);
        if (args.isEmpty()) return RANDOM.nextInt();
        if (args.size() == 1) return RANDOM.nextInt(asInt(args.get(0)));
        int min = asInt(args.get(0));
        int max = asInt(args.get(1));
        if (min >= max) throw new IllegalArgumentException("min 必须小于 max");
        return min + RANDOM.nextInt(max - min);
    }
}
