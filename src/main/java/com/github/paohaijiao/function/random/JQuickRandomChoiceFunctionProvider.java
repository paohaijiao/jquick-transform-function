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
public class JQuickRandomChoiceFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRandomChoiceFunctionProvider() {
        super("randomChoice", "从列表中随机选择一个元素 - 用法: randomChoice(list) 或 randomChoice(elem1, elem2, ...)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return null;

        List<?> source;
        if (args.size() == 1 && args.get(0) instanceof List) {
            source = (List<?>) args.get(0);
        } else {
            source = args;
        }

        if (source.isEmpty()) return null;

        int index = ThreadLocalRandom.current().nextInt(source.size());
        return source.get(index);
    }
}
