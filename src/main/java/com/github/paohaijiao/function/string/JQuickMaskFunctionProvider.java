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

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickMaskFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickMaskFunctionProvider() {
        super("mask", "掩码处理 - 用法: mask(str, start, end, maskChar?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 3, 4);
        String str = asString(args.get(0));
        int start = asInt(args.get(1));
        int end = asInt(args.get(2));
        char maskChar = args.size() > 3 ? asString(args.get(3)).charAt(0) : '*';

        if (str == null) return null;
        if (start < 0) start = 0;
        if (end > str.length()) end = str.length();
        if (start >= end) return str;

        StringBuilder sb = new StringBuilder(str);
        for (int i = start; i < end; i++) {
            sb.setCharAt(i, maskChar);
        }
        return sb.toString();
    }
}
