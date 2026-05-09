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

import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;
import java.util.List;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickGreatestInvoker extends JQuickBaseMethodInvoker {

    public JQuickGreatestInvoker() {
        super("greatest", "返回最大值 - 用法: greatest(value1, value2, ...)");
    }

    @Override
    public Object invoke(List<Object> args) {
        if (args.isEmpty()) return null;

        Object max = args.get(0);
        for (Object arg : args) {
            if (compare(arg, max) > 0) {
                max = arg;
            }
        }
        return max;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private int compare(Object a, Object b) {
        if (a == null && b == null) return 0;
        if (a == null) return -1;
        if (b == null) return 1;

        if (a instanceof Comparable && b instanceof Comparable) {
            return ((Comparable) a).compareTo(b);
        }

        return String.valueOf(a).compareTo(String.valueOf(b));
    }
}