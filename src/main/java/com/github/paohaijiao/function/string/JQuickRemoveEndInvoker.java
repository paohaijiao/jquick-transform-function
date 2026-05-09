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
public class JQuickRemoveEndInvoker extends JQuickBaseMethodInvoker {

    public JQuickRemoveEndInvoker() {
        super("removeEnd", "移除结尾后缀 - 用法: removeEnd(str, suffix, ignoreCase?)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);
        String str = asString(args.get(0));
        String suffix = asString(args.get(1));
        boolean ignoreCase = args.size() > 2 && asBoolean(args.get(2));
        if (str == null || suffix == null) return str;
        if (ignoreCase) {
            if (str.toLowerCase().endsWith(suffix.toLowerCase())) {
                return str.substring(0, str.length() - suffix.length());
            }
        } else {
            if (str.endsWith(suffix)) {
                return str.substring(0, str.length() - suffix.length());
            }
        }
        return str;
    }
}
