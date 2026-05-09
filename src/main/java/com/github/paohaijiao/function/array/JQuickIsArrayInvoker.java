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
package com.github.paohaijiao.function.array;

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickIsArrayInvoker extends JQuickBaseMethodInvoker {

    public JQuickIsArrayInvoker() {
        super("isArray", "判断是否为数组或列表 - 用法: isArray(value)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCount(args, 1);
        Object obj = args.get(0);
        return obj != null && (obj instanceof List || obj instanceof Object[]);
    }
}
