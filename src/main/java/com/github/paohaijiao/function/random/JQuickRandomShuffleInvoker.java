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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickRandomShuffleInvoker extends JQuickBaseMethodInvoker {

    public JQuickRandomShuffleInvoker() {
        super("shuffle", "随机打乱列表/数组顺序 - 用法: shuffle(list)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);

        Object source = args.get(0);
        List<Object> list;

        if (source instanceof List) {
            list = new ArrayList<>((List<?>) source);
        } else if (source instanceof Object[]) {
            list = new ArrayList<>(Arrays.asList((Object[]) source));
        } else {
            throw new IllegalArgumentException("参数必须是数组或列表");
        }

        for (int i = list.size() - 1; i > 0; i--) {
            int j = ThreadLocalRandom.current().nextInt(i + 1);
            Object temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        return list;
    }
}
