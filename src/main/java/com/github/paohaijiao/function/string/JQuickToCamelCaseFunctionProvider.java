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
public class JQuickToCamelCaseFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToCamelCaseFunctionProvider() {
        super("toCamelCase", "转驼峰命名 - 用法: toCamelCase(str, firstUpper?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        String str = asString(args.get(0));
        boolean firstUpper = args.size() > 1 && asBoolean(args.get(1));
        if (str == null) return null;
        String[] parts = str.split("[\\s-_]+");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i].toLowerCase();
            if (part.isEmpty()) continue;
            if (i == 0 && !firstUpper) {
                result.append(part);
            } else {
                result.append(Character.toUpperCase(part.charAt(0)))
                        .append(part.substring(1));
            }
        }
        return result.toString();
    }
}
