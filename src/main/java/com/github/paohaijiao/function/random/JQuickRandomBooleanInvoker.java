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
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRandomBooleanInvoker extends JQuickBaseMethodInvoker {

    public JQuickRandomBooleanInvoker() {
        super("randomBoolean", "生成随机布尔值 - 用法: randomBoolean() 或 randomBoolean(trueProbability?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 1);

        if (args.isEmpty()) {
            return ThreadLocalRandom.current().nextBoolean();
        }
        double probability = asDouble(args.get(0));
        if (probability < 0 || probability > 1) {
            throw new IllegalArgumentException("概率必须在0-1之间");
        }
        return ThreadLocalRandom.current().nextDouble() < probability;
    }
}