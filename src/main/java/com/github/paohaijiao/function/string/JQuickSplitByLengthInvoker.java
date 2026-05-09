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
package com.github.paohaijiao.function.string;

/**
 * packageName com.github.paohaijiao.function.string
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.ArrayList;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSplitByLengthInvoker extends JQuickBaseMethodInvoker {

    public JQuickSplitByLengthInvoker() {
        super("splitByLength", "按指定长度分割字符串 - 用法: splitByLength(str, chunkSize)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String str = asString(args.get(0));
        int chunkSize = asInt(args.get(1));

        if (str == null) return new ArrayList<>();
        if (chunkSize <= 0) throw new IllegalArgumentException("chunkSize必须大于0");

        List<String> chunks = new ArrayList<>();
        for (int i = 0; i < str.length(); i += chunkSize) {
            int end = Math.min(i + chunkSize, str.length());
            chunks.add(str.substring(i, end));
        }
        return chunks;
    }
}
