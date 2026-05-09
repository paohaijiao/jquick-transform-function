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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRandomSampleFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickRandomSampleFunctionProvider() {
        super("randomSample", "随机取样 - 用法: randomSample(list, count, allowRepeat?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);

        List<?> source;
        if (args.get(0) instanceof List) {
            source = (List<?>) args.get(0);
        } else {
            source = args.subList(0, args.size() - 1);
        }

        int count = asInt(args.get(1));
        boolean allowRepeat = args.size() > 2 && asBoolean(args.get(2));

        if (source.isEmpty()) return new ArrayList<>();
        if (count <= 0) return new ArrayList<>();

        if (allowRepeat) {
            List<Object> result = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                int index = ThreadLocalRandom.current().nextInt(source.size());
                result.add(source.get(index));
            }
            return result;
        } else {
            if (count > source.size()) {
                throw new IllegalArgumentException("取样数量不能大于列表大小（不允许重复时）");
            }
            List<Object> shuffled = new ArrayList<>(source);
            java.util.Collections.shuffle(shuffled, ThreadLocalRandom.current());
            return shuffled.subList(0, count);
        }
    }
}
