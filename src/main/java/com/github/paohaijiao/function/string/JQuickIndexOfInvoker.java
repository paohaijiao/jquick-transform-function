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
public class JQuickIndexOfInvoker extends JQuickBaseMethodInvoker {

    public JQuickIndexOfInvoker() {
        super("indexOf", "查找子串首次出现的位置 - 用法: indexOf(str, search, fromIndex?)");
    }
    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 2, 3);
        String str = asString(args.get(0));
        String search = asString(args.get(1));
        if (str == null || search == null) return -1;
        if (args.size() == 2) {
            return str.indexOf(search);
        } else {
            return str.indexOf(search, asInt(args.get(2)));
        }
    }
}
