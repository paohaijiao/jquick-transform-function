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
package com.github.paohaijiao.function.collection;

/**
 * packageName com.github.paohaijiao.function.collection
 *
 * @author Martin
 * @version 1.0.0
 * @since 2026/5/9
 */
import com.github.paohaijiao.function.domain.JQuickBaseMethodInvoker;
import com.github.paohaijiao.spi.anno.Priority;
import com.github.paohaijiao.spi.constants.PriorityConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

@Priority(PriorityConstants.SYSTEM_HIGH)
public class JQuickJoinInvoker extends JQuickBaseMethodInvoker {

    public JQuickJoinInvoker() {
        super("join", "连接集合元素 - 用法: join(list, delimiter)");
    }

    @Override
    public Object invoke(List<Object> args) {
        validateArgCountRange(args, 1, 2);
        Collection<?> coll = asCollection(args.get(0));
        String delimiter = args.size() > 1 ? asString(args.get(1)) : "";
        if (coll == null || coll.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (Object item : coll) {
            if (sb.length() > 0) sb.append(delimiter);
            sb.append(item != null ? item : "");
        }
        return sb.toString();
    }

    private Collection<?> asCollection(Object obj) {
        if (obj instanceof Collection) return (Collection<?>) obj;
        if (obj instanceof Object[]) return new ArrayList<>();
        if (obj != null) return Arrays.asList(obj);
        return null;
    }
}