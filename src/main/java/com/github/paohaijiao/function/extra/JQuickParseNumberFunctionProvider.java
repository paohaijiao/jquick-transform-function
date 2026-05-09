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
package com.github.paohaijiao.function.extra;

/**
 * packageName com.github.paohaijiao.function.extra
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */

import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickParseNumberFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickParseNumberFunctionProvider() {
        super("parseNumber", "解析格式化数字 - 用法: parseNumber(str, pattern)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 2);
        String str = asString(args.get(0));
        String pattern = asString(args.get(1));

        DecimalFormat df = new DecimalFormat(pattern);
        Number number = df.parse(str, new ParsePosition(0));
        if (number == null) {
            throw new IllegalArgumentException("无法解析数字: " + str);
        }
        return number.doubleValue();
    }
}
