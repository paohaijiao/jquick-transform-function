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
package com.github.paohaijiao.function.math;

/**
 * packageName com.github.paohaijiao.function.math
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseFunctionFunctionProvider;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.text.DecimalFormat;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToNumberStringFunctionProvider extends JQuickBaseFunctionFunctionProvider {

    public JQuickToNumberStringFunctionProvider() {
        super("toNumberString", "转换为数字字符串 - 用法: toNumberString(number, pattern?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        double number = asDouble(args.get(0));
        if (args.size() > 1) {
            String pattern = asString(args.get(1));
            DecimalFormat df = new DecimalFormat(pattern);
            return df.format(number);
        }
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}
