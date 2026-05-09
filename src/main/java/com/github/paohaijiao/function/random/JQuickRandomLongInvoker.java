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
public class JQuickRandomLongInvoker extends JQuickBaseMethodInvoker {

    public JQuickRandomLongInvoker() {
        super("randomLong", "生成随机长整数 - 用法: randomLong() 或 randomLong(max) 或 randomLong(min, max)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 0, 2);

        if (args.isEmpty()) {
            return ThreadLocalRandom.current().nextLong();
        }
        if (args.size() == 1) {
            long max = asLong(args.get(0));
            return ThreadLocalRandom.current().nextLong(max);
        }
        long min = asLong(args.get(0));
        long max = asLong(args.get(1));
        if (min >= max) throw new IllegalArgumentException("min 必须小于 max");
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}