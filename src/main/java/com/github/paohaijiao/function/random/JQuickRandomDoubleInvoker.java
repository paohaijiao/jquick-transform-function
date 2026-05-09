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
public class JQuickRandomDoubleInvoker extends JQuickBaseMethodInvoker {

    public JQuickRandomDoubleInvoker() {
        super("randomDouble", "生成随机浮点数 - 用法: randomDouble() 或 randomDouble(min, max)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 2);

        if (args.isEmpty()) {
            return ThreadLocalRandom.current().nextDouble();
        }
        if (args.size() == 1) {
            double max = asDouble(args.get(0));
            return ThreadLocalRandom.current().nextDouble(max);
        }
        double min = asDouble(args.get(0));
        double max = asDouble(args.get(1));
        if (min >= max) throw new IllegalArgumentException("min 必须小于 max");
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
