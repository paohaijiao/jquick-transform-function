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

/**
 * packageName com.github.paohaijiao.function.random
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRandomIntArrayFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRandomIntArrayFunctionProvider() {
        super("randomIntArray", "生成随机整数数组 - 用法: randomIntArray(size, min, max)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 3);
        int size = asInt(args.get(0));
        int min = asInt(args.get(1));
        int max = asInt(args.get(2));

        if (size <= 0) return new int[0];
        if (min >= max) throw new IllegalArgumentException("min 必须小于 max");

        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = ThreadLocalRandom.current().nextInt(min, max);
        }
        return result;
    }
}
