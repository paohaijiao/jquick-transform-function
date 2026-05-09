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

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickToCurrencyInvoker extends JQuickBaseMethodInvoker {

    public JQuickToCurrencyInvoker() {
        super("toCurrency", "转换为货币格式 - 用法: toCurrency(number, locale?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        double number = asDouble(args.get(0));

        Locale locale = Locale.CHINA;
        if (args.size() > 1) {
            String localeStr = asString(args.get(1));
            switch (localeStr.toLowerCase()) {
                case "us": locale = Locale.US; break;
                case "cn": locale = Locale.CHINA; break;
                case "uk": locale = Locale.UK; break;
                default: locale = Locale.forLanguageTag(localeStr);
            }
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(number);
    }
}
