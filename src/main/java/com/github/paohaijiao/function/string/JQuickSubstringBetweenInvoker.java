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
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickSubstringBetweenInvoker extends JQuickBaseMethodInvoker {

    public JQuickSubstringBetweenInvoker() {
        super("substringBetween", "取两个子串之间的内容 - 用法: substringBetween(str, open, close)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 3);
        String str = asString(args.get(0));
        String open = asString(args.get(1));
        String close = asString(args.get(2));
        if (str == null || open == null || close == null) return null;
        int start = str.indexOf(open);
        if (start == -1) return null;
        start += open.length();
        int end = str.indexOf(close, start);
        if (end == -1) return null;
        return str.substring(start, end);
    }
}
